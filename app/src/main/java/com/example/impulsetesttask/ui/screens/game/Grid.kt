package com.example.impulsetesttask.ui.screens.game

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Grid(
    modifier: Modifier = Modifier,
    state: GameState,
    onItemCLick: (Int) -> Unit = {}
) {
    val gridVisible = when (state.state) {
        is State.Clear, State.Initial, is State.Idle -> false
        else -> true
    }
    LazyVerticalGrid(
        modifier = modifier,
        cells = GridCells.Fixed(state.gridState.columnCount),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        content = {
            itemsIndexed(
                items = state.gridState.items,
                itemContent = { index, item ->
                    Box(modifier = Modifier.aspectRatio(1f)) {
                        val animationSpec = tween<Float>(
                            durationMillis = 1_000,
                            delayMillis = 1_000 / state.gridState.items.size * index
                        )
                        AnimatedVisibility(
                            visible = gridVisible,
                            enter = fadeIn(animationSpec),
                            exit = fadeOut(animationSpec)
                        ) {
                            Cell(
                                state = item,
                                enabled = when (state.state) {
                                    State.InProgress -> !item.isReveled
                                    else -> false
                                },
                                onClick = { onItemCLick(index) })
                        }
                    }
                }
            )
        }
    )
}