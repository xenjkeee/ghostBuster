package com.example.impulsetesttask.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.impulsetesttask.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNext: () -> Unit = {}
) {
    LaunchedEffect(Unit) {
        delay(3_000)
        onNext()
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier.align(Alignment.Center),
            painter = painterResource(id = R.drawable.game_icon),
            contentDescription = null
        )
    }
}
