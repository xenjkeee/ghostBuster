package com.example.impulsetesttask.ui.screens.game

sealed class CellType {
    object Empty : CellType()
    data class Ghost(val imageResource: Int) : CellType()
}