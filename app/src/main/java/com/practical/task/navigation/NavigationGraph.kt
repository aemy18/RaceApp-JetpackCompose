package com.practical.task.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.practical.task.ui.screens.calendar_screen.CalendarScreen
import com.practical.task.ui.screens.home_screen.HomeScreen
import com.practical.task.ui.screens.profile_screen.ProfileScreen
import com.practical.task.ui.screens.trophy_screen.TrophyScreen
import com.practical.task.ui.screens.upcoming_race_screen.UpComingRaceScreen
import com.practical.task.ui.screens.web_screen.WebScreen

@Composable
fun NavigationGraph(navController: NavHostController,innerPadding: PaddingValues) {
    NavHost(navController = navController, startDestination = Screen.HomeScreen) {
        composable<Screen.HomeScreen> { _ ->
            HomeScreen(navController = navController, innerPadding = innerPadding)
        }

        composable<Screen.CalendarScreen> { _ ->
            CalendarScreen(navController = navController, innerPadding = innerPadding)
        }

        composable<Screen.TrophyScreen> {
            val args = it.toRoute<Screen.TrophyScreen>()
            //TrophyScreen(navController,miInAppState, args.fromAction)
            TrophyScreen(navController = navController, innerPadding = innerPadding)
        }

        composable<Screen.WebScreen> {
            //val args = it.toRoute<Screen.GuessScreen>()
            //GuessScreen(navController,miInAppState,args.json)
            WebScreen(navController = navController, innerPadding = innerPadding)
        }

        composable<Screen.ProfileScreen> {
            ProfileScreen(navController = navController, innerPadding = innerPadding)
        }

        composable<Screen.UpComingScreen> {
            UpComingRaceScreen(navController = navController)
        }
    }
}