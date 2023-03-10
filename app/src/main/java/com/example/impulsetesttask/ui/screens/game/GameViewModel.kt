package com.example.impulsetesttask.ui.screens.game

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.impulsetesttask.nav.Destination
import com.example.impulsetesttask.nav.paramsKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject

@HiltViewModel
internal class GameViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val params = Json.decodeFromString<Destination.Game.Params>(
        checkNotNull(savedStateHandle.get<String>(paramsKey))
    )
    private val dimension = GameSettings.dimension(params.level)
    private val ghosts = GameSettings.ghosts(params.level)
    private val state = savedStateHandle.getStateFlow<State>("state", State.Initial)
    private val score = savedStateHandle.getStateFlow("score", params.score)
    private val map = savedStateHandle.getStateFlow("map", emptyList<CellType>())
    private val revealedCells = savedStateHandle.getStateFlow("revealed", listOf<Int>())

    private fun setState(state: State) {
        savedStateHandle["state"] = state
    }

    private fun setScore(score: Int) {
        savedStateHandle["score"] = score
    }

    private fun setMap(map: List<CellType>) {
        savedStateHandle["map"] = map
    }

    private fun setRevealedCells(cells: List<Int>) {
        savedStateHandle["revealed"] = cells
    }

    private fun revealCell(index: Int) {
        setRevealedCells(revealedCells.value + index)
        if (map.value[index] is CellType.Ghost) setScore(score.value + 5)
    }

    val gameState = combine(
        state, map, score, revealedCells
    ) { state, map, score, revealedCells ->
        GameState(
            state = state,
            score = score,
            gridState = GridState(
                columnCount = dimension.first,
                items = map.mapIndexed { index, cell ->
                    CellState(
                        isReveled = revealedCells.contains(index) ||
                                (state is State.Preview && cell is CellType.Ghost) ||
                                (state is State.Finished && cell is CellType.Ghost),
                        cellType = cell,
                        cellStatus = when {
                            !revealedCells.contains(index) -> CellStatus.Default
                            cell is CellType.Empty -> CellStatus.Wrong
                            else -> CellStatus.Correct
                        }
                    )
                }
            )
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = GameState(
            state = State.Initial,
            score = params.score,
            gridState = GridState(emptyList(), dimension.first)
        )
    )

    init {
        runStateMachine()
        handleGameFinished()
    }

    private fun handleGameFinished() = combine(
        revealedCells, map
    ) { revealedCells, map ->
        when (revealedCells.size == ghosts) {
            false -> null
            true -> State.Finished(
                revealedCells.map { i -> map[i] }
                    .filterIsInstance<CellType.Ghost>()
                    .size == ghosts
            )
        }
    }.filterNotNull().onEach { setState(it) }.launchIn(viewModelScope)

    private fun runStateMachine() = state.onEach {
        when (it) {
            State.Initial -> {
                setMap(MapGenerator.createMap(dimension, ghosts))
                setRevealedCells(emptyList())
                setScore(params.score)
                delay(100)
                setState(State.Appear)
            }
            State.Appear -> {
                delay(2_000)
                setState(State.Preview)
            }
            State.Preview -> {
                delay(3_000)
                setState(State.InProgress)
            }
            is State.Finished -> {
                delay(2_000)
                setState(State.Clear(isFinished = true))
            }
            is State.Clear -> when (it.isFinished) {
                true -> {
                    delay(2_000)
                    State.Idle(
                        revealedCells.value.map { i -> map.value[i] }
                            .filterIsInstance<CellType.Ghost>()
                            .size == ghosts
                    ).also { state -> setState(state) }
                }
                false -> {
                    delay(2_000)
                    setState(State.Initial)
                }
            }
            else -> Unit
        }
    }.launchIn(viewModelScope)

    fun handleEvent(event: GameEvent) = when (event) {
        GameEvent.Restart -> setState(State.Clear(isFinished = false))
        is GameEvent.RevealCell -> revealCell(event.index)
    }
}