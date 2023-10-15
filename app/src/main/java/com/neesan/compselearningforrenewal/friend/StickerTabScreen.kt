package com.neesan.compselearningforrenewal.friend

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

/**
 * 参考元
 * https://stackoverflow.com/questions/72240936/horizontalpager-with-lazycolumn-inside-another-lazycolumn-jetpack-compose
 */
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun StickerTabScreen(onContentSelected: (Long) -> Unit) {
    // Tabs for pager
    val tabData = listOf(
        "Tab 1",
        "Tab 2",
    )

    // Pager state
    val pagerState = rememberPagerState(pageCount = {
        tabData.size
    })

    // Coroutine scope for scroll pager
    val coroutineScope = rememberCoroutineScope()

    // Scroll behavior for TopAppBar
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    Scaffold(
        topBar = {
            TopAppBar(
                scrollBehavior = scrollBehavior,
                title = {
                    Text(text = "Top app bar")
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    scrolledContainerColor = MaterialTheme.colorScheme.surface
                ),
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                content = {
                    TabRow(
                        selectedTabIndex = pagerState.currentPage,
                        tabs = {
                            tabData.forEachIndexed { index, title ->
                                Tab(
                                    text = { Text(title) },
                                    selected = pagerState.currentPage == index,
                                    onClick = {
                                        coroutineScope.launch {
                                            pagerState.animateScrollToPage(index)
                                        }
                                    },
                                )
                            }
                        }
                    )

                    HorizontalPager(state = pagerState) { tabId ->
                        when (tabId) {
                            0 -> Tab1(scrollBehavior = scrollBehavior)
                            1 -> Tab2(scrollBehavior = scrollBehavior)
                        }
                    }
                }
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Tab1(scrollBehavior: TopAppBarScrollBehavior) {
    // List items
    val listItems = listOf(
        "test 1 tab 1",
        "test 2 tab 1",
        "test 3 tab 1",
        "test 4 tab 1",
        "test 5 tab 1",
        "test 6 tab 1",
        "test 7 tab 1",
        "test 8 tab 1",
        "test 9 tab 1",
        "test 10 tab 1",
        "test 11 tab 1",
        "test 12 tab 1",
    )

    val listState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        state = listState,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        content = {
            items(items = listItems) { item ->
                Card(
                    modifier = Modifier
                        .height(80.dp)
                        .fillMaxWidth(),

                    content = { Text(text = item) }
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Tab2(scrollBehavior: TopAppBarScrollBehavior) {
    // List items
    val listItems = listOf(
        "test 1 tab 2",
        "test 2 tab 2",
        "test 3 tab 2",
        "test 4 tab 2",
        "test 5 tab 2",
        "test 6 tab 2",
        "test 7 tab 2",
        "test 8 tab 2",
        "test 9 tab 2",
        "test 10 tab 2",
        "test 11 tab 2",
        "test 12 tab 2",
    )

    val listState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        state = listState,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        content = {
            items(items = listItems) { item ->
                Card(
                    modifier = Modifier
                        .height(80.dp)
                        .fillMaxWidth(),

                    content = { Text(text = item) }
                )
            }
        }
    )
}
