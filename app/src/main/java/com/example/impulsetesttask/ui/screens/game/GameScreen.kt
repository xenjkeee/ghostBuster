package com.example.impulsetesttask.ui.screens.game

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.impulsetesttask.R

@Composable
fun GameScreen(
    state: GameState,
    handleEvent: (GameEvent) -> Unit,
    onNext: (isCleared: Boolean, gameScore: Int) -> Unit,
) {
    LaunchedEffect(state.state) {
        when (state.state) {
            is State.Idle -> onNext(state.state.isCleared, state.score)
            else -> Unit
        }
    }
    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 180.dp)
            ) {
                AnimatedVisibility(
                    visible = when (state.state) {
                        is State.Clear, is State.Idle, State.Initial -> false
                        else -> true
                    },
                    enter = fadeIn(),
                    exit = fadeOut(),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 35.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            fontSize = 32.sp,
                            text = state.score.toString(),
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        TextButton(
                            onClick = { handleEvent(GameEvent.Restart) },
                            enabled = when (state.state) {
                                State.InProgress -> true
                                else -> false
                            },
                            shape = CircleShape,
                            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = MaterialTheme.colors.onBackground.copy(alpha = 0.2f),
                                contentColor = MaterialTheme.colors.onBackground
                            )
                        ) {
                            Text(
                                text = stringResource(id = R.string.restart),
                                fontWeight = FontWeight.W600
                            )
                        }
                    }
                }
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            Grid(
                modifier = Modifier.padding(30.dp),
                state = state,
                onItemCLick = { handleEvent(GameEvent.RevealCell(it)) }
            )
            Box(modifier = Modifier.align(Alignment.Center)) {
                AnimatedVisibility(
                    visible = when (state.state) {
                        is State.Finished -> true
                        else -> false
                    },
                    enter = fadeIn(),
                    exit = fadeOut(),
                ) {
                    when (val finishedState = state.state) {
                        is State.Finished -> Image(
                            painter = painterResource(
                                id = when (finishedState.isCleared) {
                                    true -> R.drawable.result_status_correct
                                    false -> R.drawable.result_status_incorrect
                                }
                            ),
                            contentDescription = null
                        )
                        else -> Unit
                    }
                }
            }
        }
    }
}