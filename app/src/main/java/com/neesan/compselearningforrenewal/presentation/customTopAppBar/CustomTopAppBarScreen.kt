package com.neesan.compselearningforrenewal.presentation.customTopAppBar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomTopAppBarScreen() {
    val lazyListState = rememberLazyListState()
    var tabSelected by rememberSaveable { mutableStateOf(Screen.A) }
    LazyColumn(
        state = lazyListState
    ) {
        item {
            Text(
                modifier = Modifier
                    .padding(vertical = 100.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "collapsed!"
            )
        }
        stickyHeader(key = "stickyHeader") {
            Column {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(58.dp)
                        .background(color = androidx.compose.ui.graphics.Color.Gray),
                    textAlign = TextAlign.Center,
                    text = "stickyHeader"
                )
                TabRow(
                    selectedTabIndex = tabSelected.ordinal
                ) {
                    Screen.entries.map { it.name }.forEachIndexed { index, title ->
                        Tab(
                            text = { Text(text = title) },
                            selected = tabSelected.ordinal == index,
                            onClick = { tabSelected = Screen.entries.toTypedArray()[index] }
                        )
                    }
                }
            }
        }
        when (tabSelected) {
            Screen.A -> ContentA(this@LazyColumn)
            Screen.B -> ContentB(this@LazyColumn)
        }
    }
    LaunchedEffect(tabSelected) {
        val stickyHeaderIndex =
            lazyListState.layoutInfo.visibleItemsInfo.firstOrNull { it.key == "stickyHeader" }?.index
                ?: return@LaunchedEffect
        if (lazyListState.firstVisibleItemIndex > stickyHeaderIndex) {
            lazyListState.scrollToItem(stickyHeaderIndex)
        }
    }
}


private fun ContentA(
    lazyListScope: LazyListScope
) {
    with(lazyListScope) {
        items(60) {
            Text(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "A $it"
            )
        }
    }
}

private fun ContentB(
    lazyListScope: LazyListScope
) {
    with(lazyListScope) {
        items(60) {
            Text(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "B $it"
            )
        }
    }
}

enum class Screen {
    A, B
}

@Preview(showBackground = true)
@Composable
fun プレビュー_CustomTopAppBarScreen() {
    CustomTopAppBarScreen()
}
