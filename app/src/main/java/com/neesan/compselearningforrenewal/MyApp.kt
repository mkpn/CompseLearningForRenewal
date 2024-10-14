package com.neesan.compselearningforrenewal

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.modules.presentation.home.AppDestination
import com.example.modules.presentation.home.homeScreen

@Composable
fun MyApp() {
    val navController = rememberNavController()
    // なんか違うよなぁ〜
    val contentDetail = AppDestination.ContentDetail()
    NavHost(
        navController,
        startDestination = AppDestination.Splash.route,
    ) {
        homeScreen(navController, contentDetail)
    }
}
