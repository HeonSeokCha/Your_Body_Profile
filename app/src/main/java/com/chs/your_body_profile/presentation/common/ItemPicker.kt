package com.chs.your_body_profile.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NumberPicker(
    title: String,
    items: List<Int>,
    startIdx: Int,
    onSelectItemValue: (Int) -> Unit,
    onBack: () -> Unit
) {
    val state = rememberPickerState()
    var editEnabled by remember { mutableStateOf(false) }
    ItemTitleCard(title = title) {
        Picker(
            modifier = Modifier
                .fillMaxWidth(),
            items = items,
            startIdx = startIdx,
            state = state,
            onBack = {
                onBack()
            },
            onChangeEdit = { editEnabled = !editEnabled },
            editEnabled = editEnabled
        )
        Spacer(modifier = Modifier.height(32.dp))
    }
    onSelectItemValue(state.selectedItem)
}

@Composable
fun ItemDualNumberPicker(
    title: String,
    firstItems: List<Int>,
    firstStartIdx: Int,
    secondItems: List<Int>,
    secondStartIdx: Int,
    onSelectItemValue: (Float) -> Unit,
    onBack: () -> Unit
) {
    val firstState = rememberPickerState()
    val secondState = rememberPickerState()

    var editEnabled by remember { mutableStateOf(false) }

    ItemTitleCard(title = title) {
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Picker(
                modifier = Modifier
                    .width(50.dp),
                items = firstItems,
                startIdx = firstStartIdx,
                state = firstState,
                onBack = {
                    onBack()

                },
                onChangeEdit = { editEnabled = !editEnabled },
                editEnabled = editEnabled
            )

            Text(modifier = Modifier.width(8.dp), text = ".", fontSize = 24.sp)

            Picker(
                modifier = Modifier
                    .width(50.dp),
                items = secondItems,
                startIdx = secondStartIdx,
                state = secondState,
                onBack = { onBack() },
                onChangeEdit = { editEnabled = !editEnabled },
                editEnabled = editEnabled
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
    }
    onSelectItemValue(
        (firstState.selectedItem.toFloat() + (secondState.selectedItem.toFloat() / 10f))
    )
}

@Composable
fun CollapsingNumberPicker(
    title1: String,
    title2: String,
    onSelectItemValue: (Int, Int) -> Unit
) {

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewNumberPicker() {
    Column(modifier = Modifier.fillMaxSize()) {
        NumberPicker(
            title = "혈당 (mg/dL)",
            items = (40..300).map { it },
            startIdx = 39,
            onSelectItemValue = {},
            onBack = {}
        )
    }
}