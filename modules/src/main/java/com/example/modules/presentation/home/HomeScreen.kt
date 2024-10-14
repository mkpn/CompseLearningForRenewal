package com.example.modules.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.modules.presentation.awaitTasks.AwaitTasksScreen
import com.example.modules.presentation.bottomNavigation.BottomNavigationScreen
import com.example.modules.presentation.bottomSheet.BottomSheetScreen
import com.example.modules.presentation.checkboxGroup.CheckboxGroupScreen
import com.example.modules.presentation.content.ContentScreen
import com.example.modules.presentation.customTopAppBar.ScrollWithStickyTabPagerScreen
import com.example.modules.presentation.people.PeopleScreen
import com.example.modules.presentation.pokemon.PokemonDetailScreen
import com.example.modules.presentation.reelAnimationText.ReelAnimationTextScreen
import com.example.modules.presentation.scrollWithStickyComponent.ScrollWithStickyScreen
import com.example.modules.presentation.splash.SplashScreen
import com.example.modules.presentation.stickyTab.StickyTabScreen

/**
 * AppDestinationの全クラスを網羅したリストから、その画面への同線になるCardのリストを表示する
 * Cardは2列で表示される
 * Cardにはスクリーン名とスクリーンの説明文が記載されている
 * Cardをタップすると、その画面へ遷移する
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    onCardClick: (String) -> Unit,
) {
    // AppDestinationの全クラスを網羅したリスト
    val appDestinationList = listOf(
        AppDestination.Splash,
        AppDestination.Home,
        AppDestination.BottomNavigation,
        AppDestination.ContentDetail(),
        AppDestination.CheckboxGroup,
        AppDestination.People,
        AppDestination.AwaitTasks,
        AppDestination.ReelAnimationText,
        AppDestination.ScrollWithSticky,
        AppDestination.StickyTab,
        AppDestination.PokemonDetail,
        AppDestination.BottomSheet,
        AppDestination.CustomTopAppBar
    )
    // 2列で表示する
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(appDestinationList.size) { index ->
            Card(onClick = { onCardClick(appDestinationList[index].route) }) {
                Column {
                    Text(text = appDestinationList[index].screenName)
                    Text(text = appDestinationList[index].screenDescription)
                }
            }
        }
    }
}

fun NavGraphBuilder.homeScreen(
    navController: NavHostController,
    contentDetail: AppDestination.ContentDetail
) {
    composable(AppDestination.Splash.route) {
        SplashScreen(onInitFinish = {
            navController.navigate(AppDestination.Home.route)
        })
    }
    composable(AppDestination.Home.route) {
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
    composable(AppDestination.BottomNavigation.route) {
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
    composable(AppDestination.CheckboxGroup.route) {
        CheckboxGroupScreen()
    }
    composable(AppDestination.People.route) {
        PeopleScreen(onContentSelected = {})
    }
    composable(AppDestination.ScrollWithSticky.route) {
        ScrollWithStickyScreen()
    }
    composable(AppDestination.StickyTab.route) {
        StickyTabScreen()
    }
    composable(AppDestination.ReelAnimationText.route) {
        ReelAnimationTextScreen()
    }
    composable(AppDestination.AwaitTasks.route) {
        AwaitTasksScreen()
    }
    composable(AppDestination.PokemonDetail.route) {
        PokemonDetailScreen()
    }
    composable(AppDestination.BottomSheet.route) {
        BottomSheetScreen()
    }
    composable(AppDestination.CustomTopAppBar.route) {
        ScrollWithStickyTabPagerScreen()
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

@Preview
@Composable
fun プレビュー_HomeScreenPreview() {
    HomeScreen {}
}