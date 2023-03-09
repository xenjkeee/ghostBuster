package com.example.impulsetesttask.ui.screens.game

import com.example.impulsetesttask.R
import com.example.impulsetesttask.ui.theme.ColorCellCorrect
import com.example.impulsetesttask.ui.theme.ColorCellDefault
import com.example.impulsetesttask.ui.theme.ColorCellWrong

data class CellState(
    val isReveled: Boolean,
    val cellType: CellType,
    val cellStatus: CellStatus,
)

internal val CellState.revealedBackgroundColor
    get() = when (cellStatus) {
        CellStatus.Default -> ColorCellDefault
        CellStatus.Correct -> ColorCellCorrect
        CellStatus.Wrong -> ColorCellWrong
    }

internal val CellState.revealedImageResource
    get() = when (cellType) {
        CellType.Empty -> R.drawable.ghost_wrong
        is CellType.Ghost -> cellType.imageResource
    }
