package com.neesan.compselearningforrenewal.scrollWithStickyComponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import kotlin.math.roundToInt

@Composable
fun ScrollWithStickyScreen() {
    val lazyListState = rememberLazyListState()

    var headerHeightPx = 0f
    // ヘッダー部分のoffsetポジションの高さ
    // 値が負なら基準位置より上に配置される
    val headerOffsetPx = remember { mutableFloatStateOf(0f) }

    val current = LocalDensity.current

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                //発生した縦のスクロール量 上方向にスクロールすると負の値、下方向にスクロールすると正の値
                val delta = available.y

                // スクロール分だけヘッダーのoffset位置を調整する
                // 上にスクロールされた場合は、その分だけヘッダーのoffset値がマイナスされる
                // 下にスクロールされた場合は、その分だけヘッダーのoffset値がプラスされる
                val newOffset = headerOffsetPx.floatValue + delta

                // ヘッダーのoffsetがヘッダーの高さ分だけマイナスにされていて画面から完全に消えている時
                if (headerOffsetPx.floatValue <= -headerHeightPx) {

                    //　LazyColumnも一番上までスクロールされていて、さらに下にスクロールされた時
                    return if (lazyListState.firstVisibleItemIndex == 0
                        && lazyListState.firstVisibleItemScrollOffset == 0
                        && delta > 0f
                    ) {
                        headerOffsetPx.floatValue = newOffset.coerceIn(-headerHeightPx, 0f)
                        // LazyColumnのスクロールを奪う
                        Offset(0f, delta)
                    } else {
                        // 子のLazyColumnをスクロールする
                        Offset.Zero
                    }
                } else { // ヘッダーが少しでも見えてる時
                    // ヘッダーのoffset位置を更新する
                    headerOffsetPx.floatValue = newOffset.coerceIn(-headerHeightPx, 0f)
                    // LazyColumnのスクロールを奪う
                    return Offset(0f, delta)
                }
            }
        }
    }

    val modifierWithOffsetY = Modifier.offset {
        IntOffset(
            x = 0,
            y = headerOffsetPx.floatValue.roundToInt()
        )
    }

    ConstraintLayout(
        Modifier
            .fillMaxWidth()
            // attach as a parent to the nested scroll system
            .nestedScroll(nestedScrollConnection)
    ) {
        val (header, tab, lazyColumn) = createRefs()
        Column(modifier = modifierWithOffsetY
            .constrainAs(header) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }
            .background(Color.Gray)
            .fillMaxWidth()
            .onGloballyPositioned {
                with(current) {
                    headerHeightPx = it.size.height
                        .toDp()
                        .roundToPx()
                        .toFloat()
                }
            }) {
            Text(
                text = "Header"
            )
            Text(
                text = "Header"
            )
            Text(
                text = "Header"
            )
            Text(
                text = "Header"
            )
            Text(
                text = "Header"
            )
        }
        ScrollableTabRow(
            modifier = modifierWithOffsetY.constrainAs(tab) {
                top.linkTo(header.bottom)
                start.linkTo(parent.start)
            },
            selectedTabIndex = 0,
            tabs = {
                listOf("0", "1", "3", "4", "5").forEachIndexed { index, title ->
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
                .constrainAs(lazyColumn) {
                    top.linkTo(tab.bottom)
                    start.linkTo(parent.start)
                }
                .fillMaxSize(),
            state = lazyListState,
            contentPadding = PaddingValues(bottom = 48.dp), // なぜか一番下のアイテムが見えないのでこれで誤魔化す。タブのサイズ分スクロール領域がおかしい・・・？
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
