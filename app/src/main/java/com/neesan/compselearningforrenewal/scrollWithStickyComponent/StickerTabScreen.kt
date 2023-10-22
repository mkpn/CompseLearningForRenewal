package com.neesan.compselearningforrenewal.scrollWithStickyComponent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun ScrollWithStickyComponent(onContentSelected: (Long) -> Unit) {
    val lazyListState = rememberLazyListState()
    // here we use LazyColumn that has build-in nested scroll, but we want to act like a
    // parent for this LazyColumn and participate in its nested scroll.
    // Let's make a collapsing toolbar for LazyColumn
    // Todo: どうにかしてここを可変にしなきゃ行けない　ヘッダーがレイアウトされたタイミングで高さを取得してセットしたい
    val headerHeight = 300.dp
    val headerHeightPx = with(LocalDensity.current) { headerHeight.roundToPx().toFloat() }
    // ヘッダー部分のoffsetポジションの高さ
    // 値が負なら基準位置より上に配置される
    val headerOffsetPx = remember { mutableFloatStateOf(0f) }
    // now, let's create connection to the nested scroll system and listen to the scroll
    // happening inside child LazyColumn
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                //発生した縦のスクロール量 上方向にスクロールすると負の値、下方向にスクロールすると正の値
                val delta = available.y

                // スクロール分だけヘッダーのoffset位置を調整する
                // 上にスクロールされた場合は、その分だけヘッダーのoffset値がマイナスされる
                // 下にスクロールされた場合は、その分だけヘッダーのoffset値がプラスされる
                val newOffset = headerOffsetPx.floatValue + delta

                // here's the catch: let's pretend we consumed 0 in any case, since we want
                // LazyColumn to scroll anyway for good UX
                // We're basically watching scroll without taking it
                println("デバッグ toolbarOffsetHeightPx.floatValue: ${headerOffsetPx.floatValue}")
                if (headerOffsetPx.floatValue <= -headerHeightPx) {
                    // ヘッダーのoffsetがヘッダーの高さ分だけマイナスにされていて画面から完全に消えている時

                    return if (lazyListState.firstVisibleItemIndex == 0
                        && lazyListState.firstVisibleItemScrollOffset == 0
                        && delta > 0f
                    ) {
                        //　LazyColumnも一番上までスクロールされていて、さらに下にスクロールされた時
                        headerOffsetPx.floatValue = newOffset.coerceIn(-headerHeightPx, 0f)
                        // LazyColumnのスクロールを奪う
                        Offset(0f, delta)
                    } else {
                        // LazyColumnをスクロールする
                        Offset.Zero
                    }
                } else {
                    // ヘッダーが少しでも見えてる時
                    // ヘッダーのoffset位置を更新する
                    headerOffsetPx.floatValue = newOffset.coerceIn(-headerHeightPx, 0f)
                    // LazyColumnのスクロールを奪う
                    return Offset(0f, delta)
                }
            }

            override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
                return super.onPostFling(consumed, available)
            }
        }
    }
    val modifierWithOffsetY = Modifier.offset {
        IntOffset(
            x = 0,
            y = headerOffsetPx.floatValue.roundToInt()
        )
    }
    Column(
        Modifier
            .fillMaxSize()
            // attach as a parent to the nested scroll system
            .nestedScroll(nestedScrollConnection)
    ) {
        Text(
            modifier = modifierWithOffsetY.height(headerHeight),
            text = "toolbar offset is ${headerOffsetPx.floatValue}"
        )
        TabRow(
            modifier = modifierWithOffsetY,
            selectedTabIndex = 0,
            tabs = {
                listOf("0", "1").forEachIndexed { index, title ->
                    Tab(
                        text = { Text(title) },
                        selected = false,
                        onClick = {
//                            coroutineScope.launch {
//                                pagerState.animateScrollToPage(index)
//                            }
                        },
                    )
                }
            }
        )
        // our list with build in nested scroll support that will notify us about its scroll
        LazyColumn(
            modifier = modifierWithOffsetY
                .fillMaxSize(),
            state = lazyListState
        ) {
            items(100) { index ->
                Text(
                    "I'm item $index", modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Tab1() {
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
            .fillMaxWidth(),
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
fun Tab2() {
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
            .fillMaxWidth(),
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
