package com.example.impulsetesttask.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.impulsetesttask.ui.screens.game.GameScreen
import com.example.impulsetesttask.ui.screens.game.GameViewModel
import com.example.impulsetesttask.ui.screens.home.HomeScreen
import com.example.impulsetesttask.ui.screens.results.ResultsScreen
import com.example.impulsetesttask.ui.screens.splash.SplashScreen
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


@Composable
fun NavigationHost(navController: NavHostController = rememberNavController()) {

    NavHost(
        navController = navController,
        startDestination = Destination.Splash.route
    ) {
        composable(Destination.Splash.route) {
            SplashScreen {
                navController.navigate(Destination.Home.route) {
                    popUpTo(Destination.Home.route) { inclusive = true }
                }
            }
        }

        composable(Destination.Home.route) {
            HomeScreen {
                navController.navigate(
                    Destination.Game.getRoute(Destination.Game.Params(level = 0, score = 0))
                ) {
                    popUpTo(Destination.Home.route) { inclusive = true }
                }
            }
        }

        composable(Destination.Game.route) {
            val params = Json.decodeFromString<Destination.Game.Params>(
                checkNotNull(it.arguments?.getString(paramsKey))
            )
            val viewModel = hiltViewModel<GameViewModel>()
            GameScreen(
                state = viewModel.gameState.collectAsState().value,
                handleEvent = viewModel::handleEvent
            ) { isCleared, newScore ->
                when (isCleared) {
                    true -> Destination.Results.Params(params.level + 1, newScore)
                    false -> Destination.Results.Params(params.level, newScore)
                }.also { params ->
                    navController.navigate(Destination.Results.getRoute(params)) {
                        popUpTo(Destination.Game.route) { inclusive = true }
                    }
                }
            }
        }

        composable(Destination.Results.route) {
            val params = Json.decodeFromString<Destination.Results.Params>(
                checkNotNull(it.arguments?.getString(paramsKey))
            )
            ResultsScreen(score = params.score) {
                navController.navigate(
                    Destination.Game.getRoute(
                        Destination.Game.Params(params.level, params.score)
                    )
                ) {
                    popUpTo(Destination.Results.route) { inclusive = true }
                }
            }
        }
    }
}
