package com.example.impulsetesttask.ui.screens.game

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class State : Parcelable {
    @Parcelize
    object Initial : State() //grid is empty

    @Parcelize
    object Appear : State() //appearance animation in progress. 2 sec

    @Parcelize
    object Preview : State() //display ghosts. 3 sec

    @Parcelize
    object InProgress : State() // grid is enabled. no delay

    @Parcelize
    data class Finished(val isCleared: Boolean) : State() // grid disabled. result shown. 2 sec

    @Parcelize
    data class Clear(val isFinished: Boolean) : State() // hide animation in progress. ~2 sec

    @Parcelize
    data class Idle(val isCleared: Boolean) : State() // grid is empty
}