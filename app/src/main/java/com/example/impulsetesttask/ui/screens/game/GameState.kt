package com.example.impulsetesttask.ui.screens.game

data class GameState(
    val state: State,
    val score: Int,
    val gridState: GridState,
)