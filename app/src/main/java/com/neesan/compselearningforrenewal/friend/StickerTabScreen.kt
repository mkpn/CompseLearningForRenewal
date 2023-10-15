package com.neesan.compselearningforrenewal.friend

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StickerTabScreen(onContentSelected: (Long) -> Unit) {
    var tabSelected by rememberSaveable { mutableStateOf(Screen.FRUITS) }
    LazyColumn {
        item {
            Text(
                modifier = Modifier
                    .padding(vertical = 100.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "collapsed!"
            )
        }
        stickyHeader {
            TabRow(
                selectedTabIndex = tabSelected.ordinal
            ) {
                Screen.values().map { it.name }.forEachIndexed { index, title ->
                    Tab(
                        text = { Text(text = title) },
                        selected = tabSelected.ordinal == index,
                        onClick = { tabSelected = Screen.values()[index] }
                    )
                }
            }
        }
        when (tabSelected) {
            Screen.FRUITS -> fruitsScreen(this@LazyColumn, onContentSelected)
            Screen.ANIMAL -> animalScreen(this@LazyColumn, onContentSelected)
        }
    }
}

enum class Screen {
    FRUITS, ANIMAL
}

fun fruitsScreen(
    lazyListScope: LazyListScope,
    onContentSelected: (Long) -> Unit
) {
    val fruits = listOf(
        "Apple", "Banana", "Cherry", "Date", "Grape",
        "Lemon", "Mango", "Orange", "Pineapple", "Strawberry",
        "Watermelon", "Blueberry", "Kiwi", "Peach", "Pear"
    )
    with(lazyListScope) {
        items(fruits + fruits + fruits) {
            ClickableText(
                text = AnnotatedString(it),
                style = TextStyle(
                    fontSize = 24.sp
                )
            ) {
                onContentSelected(1)
            }
        }
    }
}

fun animalScreen(
    lazyListScope: LazyListScope,
    onContentSelected: (Long) -> Unit
) {
    val animals = listOf(
        "Dog", "Cat", "Elephant", "Giraffe", "Lion",
        "Tiger", "Kangaroo", "Panda", "Hippopotamus", "Zebra",
        "Monkey", "Rabbit", "Dolphin", "Penguin", "Turtle"
    )
    with(lazyListScope) {
        items(animals + animals + animals) {
            ClickableText(
                text = AnnotatedString(it),
                style = TextStyle(
                    fontSize = 24.sp
                )
            ) {
                onContentSelected(1)
            }
        }
    }
}