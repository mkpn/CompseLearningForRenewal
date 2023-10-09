package com.neesan.compselearningforrenewal.friend

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString

@Composable
fun FriendScreen(onContentSelected: (Long) -> Unit) {
    ClickableText(text = AnnotatedString("Friend Screen")) {
        onContentSelected(2)
    }
}