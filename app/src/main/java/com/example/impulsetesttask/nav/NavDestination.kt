package com.example.impulsetesttask.nav

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface NavDestination {

    val name: String
    val hasArguments: Boolean

    val route
        get() = when (hasArguments) {
            true -> "$name?$paramsKey={$paramsKey}"
            false -> name
        }

    val args
        get() = when (hasArguments) {
            true -> listOf(navArgument(name = paramsKey) { type = NavType.StringType })
            else -> emptyList()
        }
}

const val paramsKey = "params"