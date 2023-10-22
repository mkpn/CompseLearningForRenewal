package com.neesan.compselearningforrenewal.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.neesan.compselearningforrenewal.friend.StickerTabScreen
import com.neesan.compselearningforrenewal.profile.PeopleScreen
import com.neesan.compselearningforrenewal.scrollWithStickyComponent.ScrollWithStickyScreen

@Composable
fun HomeScreen(onContentSelected: (Long) -> Unit) {
    val navController = rememberNavController()
    val items = listOf(
        HomeNavigationObjects.People,
        HomeNavigationObjects.StickyTab,
        HomeNavigationObjects.ScrollWithSticky
    )
    Scaffold(
        bottomBar = {
            MyBottomNavigation(navController, items)
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = HomeNavigationObjects.People.route,
            Modifier.padding(innerPadding)
        ) {
            composable(HomeNavigationObjects.People.route) {
                PeopleScreen(onContentSelected)
            }
            composable(HomeNavigationObjects.StickyTab.route) {
                StickerTabScreen(onContentSelected)
            }
            composable(HomeNavigationObjects.ScrollWithSticky.route) {
                ScrollWithStickyScreen(onContentSelected)
            }
        }
    }
}

@Composable
fun MyBottomNavigation(
    navController: NavHostController,
    items: List<HomeNavigationObjects>
) {
    BottomNavigation(
        backgroundColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        items.forEach { screen ->
            BottomNavigationItem(
                // IconやTextはmaterial3を使うとうまく動かない。
                // BottomNavigationItemがmaterial2なのに対してIconやTextはmaterial3なので、color周りの扱いが異なっているせいで起きてるっぽい・・・
                icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
                label = { Text(stringResource(screen.resourceId)) },
                selectedContentColor = Color.Red,
                unselectedContentColor = Color.LightGray,
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen {}
}