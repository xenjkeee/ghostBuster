package com.example.impulsetesttask.ui.screens.game

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.MeasurePolicy

/**
 * make container fill max width with giver aspect ratio if possible,
 * or make it fill max height with given aspect ratio
 */
@Composable
fun ResizeableLayout(
    modifier: Modifier = Modifier,
    ratio: Float,
    content: @Composable () -> Unit,
) = Layout(
    modifier = modifier,
    content = content,
    measurePolicy = fillSizeWithRatioMeasurePolicy(ratio)
)

private fun fillSizeWithRatioMeasurePolicy(ratio: Float) =
    MeasurePolicy { measurables, constraints ->
        val size = if (constraints.maxWidth / ratio > constraints.maxHeight.toFloat()) {
            constraints.maxHeight * ratio to constraints.maxHeight
        } else {
            constraints.maxWidth to constraints.maxWidth / ratio
        }
        val newConstraints = constraints.copy(
            maxWidth = size.first.toInt(), maxHeight = size.second.toInt()
        )
        layout(newConstraints.maxWidth, newConstraints.maxHeight) {
            measurables.forEach { it.measure(newConstraints).placeRelative(0, 0) }
        }
    }