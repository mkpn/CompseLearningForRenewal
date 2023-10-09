package com.neesan.compselearningforrenewal.home

import androidx.annotation.StringRes
import com.neesan.compselearningforrenewal.R

// 使わなくなったけどnavGraphの使い方後でわからなくなるから残しておく
//fun NavGraphBuilder.homeNavGraph() {
//    navigation(
//        route = HomeNavigationObjects.Route, // navGraphのKeyになるrouteってこと？
//        startDestination = HomeNavigationObjects.Profile.route // navGraph内で最初に表示するdestination
//    ) {
//        composable(HomeNavigationObjects.Profile.route) {
//            ProfileScreen(onContentSelected)
//        }
//        composable(HomeNavigationObjects.FriendsList.route) {
//            FriendScreen(onContentSelected)
//        }
//    }
//}

sealed class HomeNavigationObjects(val route: String, @StringRes val resourceId: Int) {
    object Profile : HomeNavigationObjects("profile", R.string.profile)
    object FriendsList : HomeNavigationObjects("friendslist", R.string.friends_list)
}