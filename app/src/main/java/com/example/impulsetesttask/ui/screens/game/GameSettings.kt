package com.example.impulsetesttask.ui.screens.game

internal object GameSettings {
    private val dimensions = listOf(4 to 4, 4 to 5, 4 to 6, 5 to 6, 5 to 7)
    private val ghosts = listOf(4, 5, 6, 7, 8)

    fun ghosts(level: Int) = ghosts[level % dimensions.size]
    fun dimension(level: Int) = dimensions[level % dimensions.size]
}