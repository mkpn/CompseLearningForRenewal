package com.neesan.compselearningforrenewal.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.neesan.compselearningforrenewal.presentation.AppDestination.*
import com.neesan.compselearningforrenewal.presentation.awaitTasks.AwaitTasksScreen
import com.neesan.compselearningforrenewal.presentation.checkboxGroup.CheckboxGroupScreen
import com.neesan.compselearningforrenewal.presentation.bottomNavigation.BottomNavigationScreen
import com.neesan.compselearningforrenewal.presentation.bottomSheet.BottomSheetScreen
import com.neesan.compselearningforrenewal.presentation.content.ContentScreen
import com.neesan.compselearningforrenewal.presentation.home.HomeScreen
import com.neesan.compselearningforrenewal.presentation.people.PeopleScreen
import com.neesan.compselearningforrenewal.presentation.pokemon.PokemonDetailScreen
import com.neesan.compselearningforrenewal.presentation.reelAnimationText.ReelAnimationTextScreen
import com.neesan.compselearningforrenewal.presentation.customTopAppBar.ScrollWithStickyTabPagerScreen
import com.neesan.compselearningforrenewal.presentation.scrollWithStickyComponent.ScrollWithStickyScreen
import com.neesan.compselearningforrenewal.presentation.splash.SplashScreen
import com.neesan.compselearningforrenewal.presentation.stickyTab.StickyTabScreen

@Composable
fun MyApp() {
    val navController = rememberNavController()
    // なんか違うよなぁ〜
    val contentDetail = ContentDetail()
    NavHost(
        navController,
        startDestination = Splash.route,
    ) {
        composable(Splash.route) {
            SplashScreen(onInitFinish = {
                navController.navigate(Home.route)
            })
        }
        composable(Home.route) {
            HomeScreen(
                onCardClick = {
                    if (it != contentDetail.route) {
                        navController.navigate(it)
                    } else {
                        navController.navigate("${contentDetail.route}/1")
                    }
                }
            )
        }
        composable(BottomNavigation.route) {
            BottomNavigationScreen(
                onContentSelected = { contentId ->
                    navController.navigate("${contentDetail.route}/$contentId")
                }
            )
        }
        composable(
            "${contentDetail.route}/{${contentDetail.contentIdKey}}",
            arguments = listOf(navArgument(contentDetail.contentIdKey) {
                type = NavType.LongType
            }), content = {
                ContentScreen(
                    contentId = it.arguments?.getLong(contentDetail.contentIdKey) ?: 0L
                )
            }
        )
        composable(CheckboxGroup.route) {
            CheckboxGroupScreen()
        }
        composable(People.route) {
            PeopleScreen(onContentSelected = {})
        }
        composable(ScrollWithSticky.route) {
            ScrollWithStickyScreen()
        }
        composable(StickyTab.route) {
            StickyTabScreen(onContentSelected = {})
        }
        composable(ReelAnimationText.route) {
            ReelAnimationTextScreen()
        }
        composable(AwaitTasks.route) {
            AwaitTasksScreen()
        }
        composable(PokemonDetail.route) {
            PokemonDetailScreen()
        }
        composable(BottomSheet.route) {
            BottomSheetScreen()
        }
        composable(CustomTopAppBar.route) {
            ScrollWithStickyTabPagerScreen()
        }
    }
}

/**
 * アプリ内で利用する画面の定義
 * @param route ルート名 (Navigationで利用する)
 * @param screenName 画面名 (画面遷移時に利用する。画面に表示するので日本語)
 * @param screenDescription 画面の説明 (画面遷移時に利用する。画面に表示するので日本語)
 */
sealed class AppDestination(
    val route: String,
    val screenName: String,
    val screenDescription: String
) {
    data object Splash :
        AppDestination(AppDestinations.SPLASH_ROUTE, "スプラッシュ", "スプラッシュ画面")

    data object Home : AppDestination(AppDestinations.HOME_ROUTE, "ホーム", "ホーム画面")

    /**
     * content idのkeyを追加で持つ
     */
    data class ContentDetail(val contentIdKey: String = AppDestinations.CONTENT_ID_KEY) :
        AppDestination(
            AppDestinations.CONTENT_DETAIL_ROUTE,
            "コンテンツ詳細",
            "チェックボックスのリストとかMAPとか"
        )

    data object BottomNavigation :
        AppDestination(
            AppDestinations.BOTTOM_NAVIGATION_ROUTE,
            "ボトムナビゲーション",
            "ボトムナビゲーション画面"
        )

    data object ReelAnimationText : AppDestination(
        AppDestinations.REEL_ANIMATION_TEXT_ROUTE,
        "リールアニメーションテキスト",
        "リールアニメーションテキスト画面"
    )

    data object AwaitTasks :
        AppDestination(AppDestinations.AWAIT_TASKS_ROUTE, "AwaitTasks", "AwaitTasks画面")

    data object CheckboxGroup :
        AppDestination(AppDestinations.CHECKBOX_GROUP_ROUTE, "CheckboxGroup", "CheckboxGroup画面")

    data object People : AppDestination(AppDestinations.PEOPLE_ROUTE, "People", "People画面")
    data object ScrollWithSticky : AppDestination(
        AppDestinations.SCROLL_WITH_STICKY_ROUTE,
        "ScrollWithSticky",
        "ScrollWithSticky画面"
    )

    data object StickyTab :
        AppDestination(AppDestinations.STICKY_TAB_ROUTE, "StickyTab", "StickyTab画面")

    data object PokemonDetail :
        AppDestination(
            AppDestinations.POKEMON_DETAIL_ROUTE,
            "pokemon detail",
            "PokeAPIからデータ取得"
        )

    data object BottomSheet :
        AppDestination(
            AppDestinations.BOTTOM_SHEET_ROUTE,
            "BottomSheet",
            "BottomSheetのデザイン変更"
        )

    data object CustomTopAppBar :
        AppDestination(
            AppDestinations.CUSTOM_TOP_APP_BAR,
            "CustomTopAppBar",
            "collapse感のあるapp barの実装"
        )

    companion object {
        object AppDestinations {
            const val SPLASH_ROUTE = "splash"
            const val HOME_ROUTE = "home"
            const val BOTTOM_NAVIGATION_ROUTE = "bottom_navigation"
            const val REEL_ANIMATION_TEXT_ROUTE = "reel_animation_text"
            const val AWAIT_TASKS_ROUTE = "await_tasks"
            const val CHECKBOX_GROUP_ROUTE = "checkbox_group"
            const val PEOPLE_ROUTE = "people"
            const val SCROLL_WITH_STICKY_ROUTE = "scroll_with_sticky"
            const val STICKY_TAB_ROUTE = "sticky_tab"
            const val CONTENT_DETAIL_ROUTE = "content"
            const val CONTENT_ID_KEY = "contentId"
            const val POKEMON_DETAIL_ROUTE = "pokemon_detail"
            const val BOTTOM_SHEET_ROUTE = "bottom_sheet"
            const val CUSTOM_TOP_APP_BAR = "custom_top_app_bar"
        }
    }

}