package com.example.impulsetesttask.ui.screens.game

sealed class GameEvent {
    object Restart : GameEvent()
    data class RevealCell(val index: Int) : GameEvent()
}