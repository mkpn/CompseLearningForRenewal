package com.neesan.compselearningforrenewal

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.neesan.compselearningforrenewal.checkboxGroup.CheckBoxGroupScreen
import com.neesan.compselearningforrenewal.home.HomeScreen
import com.neesan.compselearningforrenewal.splash.SplashScreen

@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(
        navController,
        startDestination = AppDestinations.SPLASH,
    ) {
        composable(AppDestinations.SPLASH) {
            SplashScreen(onInitFinish = {
                navController.navigate(AppDestinations.HOME_ROUTE)
            })
        }
        composable(AppDestinations.HOME_ROUTE) {
            HomeScreen(
                onContentSelected = { contentId ->
                    navController.navigate("${AppDestinations.CONTENT_DETAIL_ROUTE}/$contentId")
                }
            )
        }
        composable(
            "${AppDestinations.CONTENT_DETAIL_ROUTE}/{${AppDestinations.CONTENT_ID_KEY}}",
            arguments = listOf(navArgument(AppDestinations.CONTENT_ID_KEY) {
                type = NavType.LongType
            })
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val snackId = arguments.getLong(AppDestinations.CONTENT_ID_KEY)
            CheckBoxGroupScreen(snackId)
        }
    }
}

object AppDestinations {
    const val SPLASH = "splash"
    const val HOME_ROUTE = "home"
    const val CONTENT_DETAIL_ROUTE = "content"
    const val CONTENT_ID_KEY = "contentId"
}