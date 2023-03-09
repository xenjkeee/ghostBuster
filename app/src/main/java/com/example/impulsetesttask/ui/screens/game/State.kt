package com.example.impulsetesttask.ui.screens.game

sealed class State {
    object Initial : State() //grid is empty
    object Appear : State() //appearance animation in progress. 2 sec
    object Preview : State() //display ghosts. 3 sec
    object InProgress : State() // grid is enabled. no delay
    data class Finished(val isCleared: Boolean) : State() // grid disabled. result shown. 2 sec
    data class Clear(val isFinished: Boolean) : State() // hide animation in progress. ~2 sec
    data class Idle(val isCleared: Boolean) : State() // grid is empty
}