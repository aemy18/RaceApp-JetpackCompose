package com.practical.task

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.practical.task.core.extension.launchScreen
import com.practical.task.core.extension.logd
import com.practical.task.navigation.NavigationGraph
import com.practical.task.navigation.Screen
import com.practical.task.ui.theme.TaskTheme
import com.practical.task.ui.theme.colorBlack
import com.practical.task.ui.theme.selectedTabColor
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

data class BottomNavigationItem(
    val routeName: Screen,
    val selectedIcon: Int,
    val unSelectedIcon: Int
)

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()
        setContent {
            TaskTheme {
                navController = rememberNavController()

                var isSetNavGraph by remember { mutableStateOf(false) }
                LaunchedEffect(Unit) {
                    "Launched Effect called".logd()
                    delay(200)
                    isSetNavGraph = true
                }

                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                    ?: Screen.HomeScreen::class.java.simpleName

                val items = listOf(
                    BottomNavigationItem(
                        routeName = Screen.HomeScreen,
                        selectedIcon = R.drawable.ic_selected_home,
                        unSelectedIcon = R.drawable.ic_unselected_home
                    ),
                    BottomNavigationItem(
                        routeName = Screen.CalendarScreen,
                        selectedIcon = R.drawable.ic_selected_calendar,
                        unSelectedIcon = R.drawable.ic_unselected_calendar
                    ),
                    BottomNavigationItem(
                        routeName = Screen.TrophyScreen(""),
                        selectedIcon = R.drawable.ic_selected_trophy,
                        unSelectedIcon = R.drawable.ic_unselected_trophy
                    ),
                    BottomNavigationItem(
                        routeName = Screen.WebScreen(""),
                        selectedIcon = R.drawable.ic_selected_earth,
                        unSelectedIcon = R.drawable.ic_unselected_earth
                    ),
                    BottomNavigationItem(
                        routeName = Screen.ProfileScreen(""),
                        selectedIcon = R.drawable.ic_selected_profile,
                        unSelectedIcon = R.drawable.ic_unselected_profile
                    ),
                )
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        NavigationBar(containerColor = colorBlack) {
                            items.forEach { item ->
                                val isSelected = when {
                                    currentRoute.contains("UpComingScreen") &&
                                            item.routeName::class.java.simpleName == "HomeScreen" -> true
                                    else -> currentRoute.contains(item.routeName::class.java.simpleName)
                                }
                                NavigationBarItem(
                                    selected = isSelected,
                                    onClick = {
                                        if (!isSelected) {
                                            navController.launchScreen(item.routeName)
                                        }
                                    },
                                    icon = {
                                        Image(
                                            painter = painterResource(
                                                if (isSelected)
                                                    item.selectedIcon
                                                else
                                                    item.unSelectedIcon
                                            ),
                                            contentDescription = item.routeName.toString()
                                        )
                                    },
                                    colors = NavigationBarItemDefaults.colors(
                                        indicatorColor = selectedTabColor, // disable default ripple background
                                    )
                                )
                            }
                        }
                    },
                    contentWindowInsets = androidx.compose.foundation.layout.WindowInsets(0)
                ) { innerPadding ->
                    if (isSetNavGraph && ::navController.isInitialized) {
                        "Nav Graph set called".logd()
                        NavigationGraph(navController, innerPadding)
                    }
                }

                onBackPressedDispatcher.addCallback(this@MainActivity) {
                    val currentRoute = navController.currentBackStackEntry?.destination?.route
                        ?: Screen.HomeScreen.toString()

                    if (!currentRoute.contains(Screen.HomeScreen.toString())) {
                        // Pop screens until HomeScreen
                        navController.popBackStack(Screen.HomeScreen, false)
                    } else {
                        // Already on HomeScreen, exit app
                        finish()
                    }
                }

            }
        }
    }
}