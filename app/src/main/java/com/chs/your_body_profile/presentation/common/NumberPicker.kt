package com.chs.your_body_profile.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun NumberPicker(
    title: String,
    items: List<String>,
    onSelectItemValue: (String) -> Unit
) {
    val state = rememberPickerState()
    Card {
        Text(
            text = title,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Picker(
            modifier = Modifier,
            items = items,
            state = state
        )
    }
    onSelectItemValue(state.selectedItem)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewNumberPicker() {
    Column(modifier = Modifier.fillMaxSize()) {
        NumberPicker(
            title = "혈당 (mg/dL)",
            items = (30..300).map { it.toString() }, onSelectItemValue = {
            })
    }
}