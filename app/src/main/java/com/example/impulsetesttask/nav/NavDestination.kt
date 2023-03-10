package com.example.impulsetesttask.nav

interface NavDestination {

    val name: String
    val hasArguments: Boolean

    val route
        get() = when (hasArguments) {
            true -> "$name?$paramsKey={$paramsKey}"
            false -> name
        }
}

const val paramsKey = "params"