package com.neesan.compselearningforrenewal.content

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ContentScreen(contentId: Long) {
    Text(text = "contentId is $contentId")
}