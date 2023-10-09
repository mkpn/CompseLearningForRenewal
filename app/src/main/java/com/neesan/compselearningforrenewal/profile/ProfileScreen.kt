package com.neesan.compselearningforrenewal.profile

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString

@Composable
fun ProfileScreen(onContentSelected: (Long) -> Unit) {
    ClickableText(text = AnnotatedString("Profile Screen")) {
        onContentSelected(1)
    }
}