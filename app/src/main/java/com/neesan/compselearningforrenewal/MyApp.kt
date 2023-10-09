package com.neesan.compselearningforrenewal

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.neesan.compselearningforrenewal.home.HomeNavigationObjects
import com.neesan.compselearningforrenewal.home.HomeScreen

@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(
        navController,
        startDestination = HomeNavigationObjects.Route,
    ) {
        composable(HomeNavigationObjects.Route) {
            HomeScreen()
        }
        composable("ToDo") {
//            ProfileScreen()
        }
    }
}
