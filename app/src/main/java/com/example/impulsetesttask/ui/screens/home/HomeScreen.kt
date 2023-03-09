package com.example.impulsetesttask.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.impulsetesttask.R

@Composable
fun HomeScreen(onNext: () -> Unit = {}) = Box(
    modifier = Modifier.fillMaxSize()
) {
    TextButton(
        modifier = Modifier
            .align(Alignment.Center)
            .width(240.dp),
        shape = CircleShape,
        onClick = onNext,
        contentPadding = PaddingValues(14.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.onBackground,
            contentColor = MaterialTheme.colors.primary,
        )
    ) {
        Text(
            text = stringResource(id = R.string.start_game),
            fontWeight = FontWeight.W600,
        )
    }
}