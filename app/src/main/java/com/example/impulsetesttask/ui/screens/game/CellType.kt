package com.example.impulsetesttask.ui.screens.game

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class CellType : Parcelable {
    @Parcelize
    object Empty : CellType()

    @Parcelize
    data class Ghost(val imageResource: Int) : CellType()
}