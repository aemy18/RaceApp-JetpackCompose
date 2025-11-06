package com.practical.task.ui.screens.web_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.practical.task.ui.theme.colorBlack

@Composable
fun WebScreen(navController: NavHostController,innerPadding: PaddingValues){
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Web Screen", style = MaterialTheme.typography.titleLarge.copy(color = colorBlack, fontSize = 14.sp))
    }
}