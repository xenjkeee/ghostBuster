package com.example.impulsetesttask.ui.screens.game

import com.example.impulsetesttask.R

internal object MapGenerator {

    fun createMap(dimension: MapDimension, ghostCount: Int) = buildList {
        val size = dimension.first * dimension.second
        repeat(ghostCount) { add(CellType.Ghost(ghostImageResources[it % ghostImageResources.size])) }
        repeat(size - ghostCount) { add(CellType.Empty) }
    }.shuffled()

    private val ghostImageResources = listOf(
        R.drawable.ghost_1, R.drawable.ghost_2, R.drawable.ghost_3,
        R.drawable.ghost_4, R.drawable.ghost_5,
    )
}