package com.neesan.compselearningforrenewal.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.neesan.compselearningforrenewal.presentation.AppDestination.AwaitTasks
import com.neesan.compselearningforrenewal.presentation.AppDestination.BottomNavigation
import com.neesan.compselearningforrenewal.presentation.AppDestination.CheckboxGroup
import com.neesan.compselearningforrenewal.presentation.AppDestination.ContentDetail
import com.neesan.compselearningforrenewal.presentation.AppDestination.Home
import com.neesan.compselearningforrenewal.presentation.AppDestination.People
import com.neesan.compselearningforrenewal.presentation.AppDestination.ReelAnimationText
import com.neesan.compselearningforrenewal.presentation.AppDestination.ScrollWithSticky
import com.neesan.compselearningforrenewal.presentation.AppDestination.Splash
import com.neesan.compselearningforrenewal.presentation.AppDestination.StickyTab

/**
 * AppDestinationの全クラスを網羅したリストから、その画面への同線になるCardのリストを表示する
 * Cardは2列で表示される
 * Cardにはスクリーン名とスクリーンの説明文が記載されている
 * Cardをタップすると、その画面へ遷移する
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(onCardClick: (String) -> Unit) {
    // AppDestinationの全クラスを網羅したリスト
    val appDestinationList = listOf(
        Splash,
        Home,
        BottomNavigation,
        ContentDetail(),
        CheckboxGroup,
        People,
        AwaitTasks,
        ReelAnimationText,
        ScrollWithSticky,
        StickyTab
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

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen {}
}