package com.practical.task.core.extension

import androidx.annotation.IdRes
import androidx.navigation.NavHostController
import com.practical.task.navigation.Screen

fun NavHostController.safelyCloseCurrentScreen() {
    popBackStack()
}


fun NavHostController.safelyCloseMultipleScreen(
    @IdRes destination: Int,
    inclusive: Boolean,
) {
    popBackStack(
        destinationId = destination,
        inclusive = inclusive
    )
}

fun NavHostController.safelyClearBackStackScreen(route: String) {
    val currentRoute = this.currentBackStackEntry?.destination?.route
    this.navigate(route) {
        popUpTo(currentRoute ?: "") {
            inclusive = true
        }
    }
}

fun NavHostController.safelyClearBackStackScreen(route: String, data: String = "") {
    val currentRoute = this.currentBackStackEntry?.destination?.route
    this.navigate(if (data.isNotEmpty()) "$route/$data" else route) {
        popUpTo(currentRoute ?: "") {
            inclusive = true
        }
    }
}

fun NavHostController.launchScreen(route: String, data: String = "") {
    if (data.isNotEmpty()) {
        navigate("$route/$data")
    } else {
        navigate(route)
    }
}


fun NavHostController.safelyClearBackStackScreen(route: Screen) {
    this.navigate(route) {
        popUpTo(this@safelyClearBackStackScreen.graph.id) {
            inclusive = true
        }
    }
}

fun NavHostController.launchScreen(route: Screen) {
    navigate(route)
}
