package com.practical.task.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object HomeScreen : Screen()

    @Serializable
    data object CalendarScreen : Screen()

    @Serializable
    data class TrophyScreen(val fromAction: String) : Screen()

    @Serializable
    data class WebScreen(val json:String) : Screen()

    @Serializable
    data class ProfileScreen(val json:String) : Screen()

    @Serializable
    data class UpComingScreen(val json:String) : Screen()
}