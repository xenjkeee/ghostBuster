package com.example.impulsetesttask.ui.screens.results

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.impulsetesttask.R

@Composable
fun ResultsScreen(
    score: Int,
    onNext: () -> Unit = {}
) = Scaffold(
    bottomBar = {
        TextButton(
            modifier = Modifier
                .padding(36.dp)
                .fillMaxWidth(),
            shape = CircleShape,
            onClick = onNext,
            contentPadding = PaddingValues(14.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.onBackground,
                contentColor = MaterialTheme.colors.primary,
            )
        ) {
            Text(text = stringResource(id = R.string.next_level))
        }
    }
) { paddingValues ->
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = score.toString(),
            fontSize = 72.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = stringResource(id = R.string.score),
            fontSize = 32.sp,
            fontWeight = FontWeight.W600,
        )
    }
}