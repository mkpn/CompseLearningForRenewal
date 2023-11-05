package com.neesan.compselearningforrenewal.checkboxGroup

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class CheckboxItem(val isChecked: Boolean, val label: String)

// 呼び出し側の関数の実装
@Composable
fun CheckboxGroupScreen() {
    val checkboxItems = remember {
        listOf(
            CheckboxItem(false, "Item 1"),
            CheckboxItem(false, "Item 2"),
            CheckboxItem(false, "Item 3"),
            CheckboxItem(false, "Item 4"),
            CheckboxItem(false, "Item 5")
        )
    }

    CheckBoxGroupContent(
        checkboxItemList = checkboxItems
    )
}

@Composable
private fun CheckBoxGroupContent(
    checkboxItemList: List<CheckboxItem>
) {
    val modifiedCheckboxItemList = remember { mutableStateListOf(*checkboxItemList.toTypedArray()) }
    var isListExpanded by remember { mutableStateOf(false) }

    val parentState = modifiedCheckboxItemList.all { it.isChecked }
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = parentState,
                onCheckedChange = { isChecked ->
                    modifiedCheckboxItemList.replaceAll { it.copy(isChecked = isChecked) }
                }
            )
            Spacer(Modifier.size(16.dp))
            Text(
                modifier = Modifier.clickable { isListExpanded = !isListExpanded },
                text = if (isListExpanded) "Close" else "Open"
            )
        }

        if (isListExpanded) {
            LazyColumn(modifier = Modifier.padding(start = 10.dp)) {
                items(checkboxItemList.size) { index ->
                    val item = modifiedCheckboxItemList[index]
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = item.isChecked,
                            onCheckedChange = { isChecked ->
                                modifiedCheckboxItemList[index] = item.copy(isChecked = isChecked)
                            }
                        )
                        Spacer(Modifier.size(16.dp))
                        Text(text = item.label)
                    }
                }
            }
        }
    }
}
