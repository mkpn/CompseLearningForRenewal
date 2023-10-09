package com.neesan.compselearningforrenewal.home

import androidx.annotation.StringRes
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.neesan.compselearningforrenewal.R
import com.neesan.compselearningforrenewal.friend.FriendScreen
import com.neesan.compselearningforrenewal.profile.ProfileScreen

fun NavGraphBuilder.homeNavGraph() {
    navigation(
        route = HomeNavigationObjects.Route,
        startDestination = HomeNavigationObjects.Profile.route
    ) {
        composable(HomeNavigationObjects.Profile.route) {
            ProfileScreen()
        }
        composable(HomeNavigationObjects.FriendsList.route) {
            FriendScreen()
        }
    }
}

sealed class HomeNavigationObjects(val route: String, @StringRes val resourceId: Int) {
    object Profile : HomeNavigationObjects("profile", R.string.profile)
    object FriendsList : HomeNavigationObjects("friendslist", R.string.friends_list)
    companion object {
        const val Route = "home route"
    }
}