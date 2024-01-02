package com.neesan.compselearningforrenewal.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.neesan.compselearningforrenewal.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onInitFinish: () -> Unit) {
    SplashContent(onInitFinish)
}

@Composable
private fun SplashContent(onInitFinish: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(200)
        onInitFinish()
    }

    Image(
        painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = null,
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize()
    )
    Image(
        painterResource(id = R.drawable.ic_launcher_foreground),
        contentDescription = null,
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize()
    )
}

@Preview
@Composable
fun SplashContentPreview() {
    SplashContent(onInitFinish = {})
}