package com.example.impulsetesttask.nav

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

sealed class Destination : NavDestination {

    object Home : Destination() {
        override val name: String = "home"
        override val hasArguments: Boolean = false
    }

    object Game : Destination() {
        override val name: String = "game"
        override val hasArguments: Boolean = true

        @Serializable
        data class Params(
            @SerialName("level") val level: Int,
            @SerialName("score") val score: Int
        )

        fun getRoute(params: Params) = "$name?$paramsKey=${Json.encodeToString(params)}"
    }

    object Results : Destination() {
        override val name: String = "results"
        override val hasArguments: Boolean = true

        @Serializable
        data class Params(
            @SerialName("level") val level: Int,
            @SerialName("score") val score: Int
        )

        fun getRoute(params: Params) = "${name}?$paramsKey=${Json.encodeToString(params)}"
    }
}