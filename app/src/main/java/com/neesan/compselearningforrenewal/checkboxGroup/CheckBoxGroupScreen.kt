package com.neesan.compselearningforrenewal.checkboxGroup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
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
fun CheckBoxGroupContent(
    checkboxItemList: List<CheckboxItem>
) {
    val modifiedCheckboxItemList = remember { mutableStateListOf(*checkboxItemList.toTypedArray()) }

    val parentState = modifiedCheckboxItemList.all { it.isChecked }
    Column {
        Checkbox(
            checked = parentState,
            onCheckedChange = { isChecked ->
                modifiedCheckboxItemList.replaceAll { it.copy(isChecked = isChecked) }
            }
        )

        Spacer(Modifier.size(25.dp))
        Column(Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp)) {
            modifiedCheckboxItemList.forEachIndexed { index, checkboxItem ->
                Checkbox(
                    checked = checkboxItem.isChecked,
                    onCheckedChange = { isChecked ->
                        modifiedCheckboxItemList[index] = checkboxItem.copy(isChecked = isChecked)
                    }
                )
                Spacer(Modifier.size(25.dp))
            }
        }
    }
}
