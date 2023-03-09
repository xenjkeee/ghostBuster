package com.example.impulsetesttask.ui.screens.game

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.impulsetesttask.ui.theme.ColorCellEmpty


@Composable
fun Cell(
    state: CellState,
    enabled: Boolean,
    onClick: () -> Unit
) = Box(
    modifier = Modifier
        .aspectRatio(1f)
        .background(
            color = ColorCellEmpty,
            shape = RoundedCornerShape(2.dp)
        )
        .clickable(enabled = enabled) { onClick() }
) {
    AnimatedVisibility(
        visible = state.isReveled,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = state.revealedBackgroundColor,
                    shape = RoundedCornerShape(2.dp)
                ),
            painter = painterResource(id = state.revealedImageResource),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
    }
}
