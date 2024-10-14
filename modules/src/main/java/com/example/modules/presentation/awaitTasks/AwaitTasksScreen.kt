package com.example.modules.presentation.awaitTasks


import androidx.compose.material.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.neesan.compselearningforrenewal.domain.reository.TestRepository
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.delay

@Composable
fun AwaitTasksScreen(repository: TestRepository = TestRepository()) {
    var dialogText by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    var needShowFirstDialog by remember { mutableStateOf(false) }
    val firstResult = CompletableDeferred<Boolean>()
    var needShowSecondDialog by remember { mutableStateOf(false) }
    val secondResult = CompletableDeferred<Boolean>()
    var needShowThirdDialog by remember { mutableStateOf(false) }
    val thirdResult = CompletableDeferred<Boolean>()

    // ダイアログ表示のロジック
    val showFirstDialog: @Composable () -> Unit = {
        needShowFirstDialog = true
    }
    if (needShowFirstDialog) {
        CustomDialog(
            onConfirm = { firstResult.complete(true) },  // OKが押されたらtrueを返す
            onCancel = { firstResult.complete(false) }   // キャンセルが押されたらfalseを返す
        )
    }

    if (needShowSecondDialog) {
        CustomDialog(
            onConfirm = { secondResult.complete(true) },  // OKが押されたらtrueを返す
            onCancel = { secondResult.complete(false) }   // キャンセルが押されたらfalseを返す
        )
    }

    SomeFun()
}


@Composable
fun SomeFun() {
    LaunchedEffect(Unit) {
        someCoroutineTaskA()  // この関数の完了を待つ
        someCoroutineTaskB()  // someCoroutineTaskA が完了した後に実行
    }
}

suspend fun someCoroutineTaskA() {
    // 非同期タスクAの内容
    println("デバッグ someCoroutineTaskA")
    delay(2000)
}

suspend fun someCoroutineTaskB() {
    // 非同期タスクBの内容
    println("デバッグ someCoroutineTaskB")
}


// ダイアログが閉じられるのを待つサスペンド関数
suspend fun awaitDialogClose(showDialog: Boolean) {
    while (showDialog) {
        delay(100)
    }
}

@Composable
fun CustomDialog(
    onConfirm: () -> Unit,
    onCancel: () -> Unit
) {
    val showDialog by remember { mutableStateOf(true) }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { onCancel() },
            text = { Text("dialogText") },
            confirmButton = {
                TextButton(onClick = { onConfirm() }) {
                    Text("OK")
                }
            }
        )
    }
}
