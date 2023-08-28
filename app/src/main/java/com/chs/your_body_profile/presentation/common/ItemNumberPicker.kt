package com.chs.your_body_profile.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun NumberPicker(
    title: String,
    items: List<Int>,
    startIdx: Int,
    onSelectItemValue: (Int) -> Unit,
    onBack: () -> Unit
) {
    val state = rememberPickerState()
    ItemTitleCard(title = title) {
        Picker(
            modifier = Modifier,
            items = items,
            startIdx = startIdx,
            state = state,
            onBack = { onBack() }
        )
        Spacer(modifier = Modifier.height(32.dp))
    }
    onSelectItemValue(state.selectedItem)
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