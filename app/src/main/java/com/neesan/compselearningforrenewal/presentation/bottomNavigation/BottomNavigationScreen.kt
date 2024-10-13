package com.neesan.compselearningforrenewal.presentation.bottomNavigation

import androidx.annotation.StringRes
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
import com.neesan.compselearningforrenewal.R
import com.neesan.compselearningforrenewal.presentation.stickyTab.StickyTabScreen
import com.neesan.compselearningforrenewal.presentation.people.PeopleScreen
import com.neesan.compselearningforrenewal.presentation.scrollWithStickyComponent.ScrollWithStickyScreen

@Composable
fun BottomNavigationScreen(onContentSelected: (Long) -> Unit) {
    val navController = rememberNavController()
    val items = listOf(
        BottomNavDestination.People,
        BottomNavDestination.StickyTab,
        BottomNavDestination.ScrollWithSticky
    )
    Scaffold(
        bottomBar = {
            MyBottomNavigation(navController, items)
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = BottomNavDestination.People.route,
            Modifier.padding(innerPadding)
        ) {
            composable(BottomNavDestination.People.route) {
                PeopleScreen(onContentSelected)
            }
            composable(BottomNavDestination.StickyTab.route) {
                StickyTabScreen()
            }
            composable(BottomNavDestination.ScrollWithSticky.route) {
                ScrollWithStickyScreen()
            }
        }
    }
}

@Composable
fun MyBottomNavigation(
    navController: NavHostController,
    items: List<BottomNavDestination>
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

sealed class BottomNavDestination(val route: String, @StringRes val resourceId: Int) {
    object People : BottomNavDestination("people", R.string.people)
    object StickyTab : BottomNavDestination("stickyTab", R.string.sticky_tab)
    object ScrollWithSticky : BottomNavDestination("scrollWithSticky", R.string.sticky_tab)
}

@Preview
@Composable
fun HomeScreenPreview() {
    BottomNavigationScreen {}
}