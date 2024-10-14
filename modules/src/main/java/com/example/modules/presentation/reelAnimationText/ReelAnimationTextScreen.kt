package com.example.modules.presentation.reelAnimationText

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.modules.presentation.theme.CompseLearningForRenewalTheme
import kotlinx.coroutines.delay
import java.text.DecimalFormat
import kotlin.random.Random

@Composable
fun ReelAnimationTextScreen() {
    var isRunning by remember { mutableStateOf(false) }
    var displayedNumber by remember { mutableStateOf("0") }
    val formatter = DecimalFormat("#,###")

    LaunchedEffect(isRunning) {
        while (isRunning) {
            displayedNumber = formatter.format(Random.nextInt(0, 999999))
            delay(100) // 0.1秒待機
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = displayedNumber,
            style = MaterialTheme.typography.h4
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Button(onClick = { isRunning = true }) {
                Text("開始")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { isRunning = false }) {
                Text("停止")
            }
        }
    }
}

@Preview
@Composable
fun プレビュー_ReelAnimationTextScreen() {
    CompseLearningForRenewalTheme {
        ReelAnimationTextScreen()
    }
}
