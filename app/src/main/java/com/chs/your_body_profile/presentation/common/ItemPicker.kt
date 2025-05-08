package com.chs.your_body_profile.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.chs.your_body_profile.common.Constants
import java.time.LocalDate
import java.time.LocalDateTime

@Composable
fun <T> ItemPicker(
    title: String,
    items: List<T>,
    startIdx: Int,
    onSelectItemValue: (T) -> Unit,
    onBack: () -> Unit
) {
    val state = rememberPickerState<T>()
    var editEnabled by remember { mutableStateOf(false) }

    ItemTitleCard(title = title) {
        Picker(
            modifier = Modifier
                .fillMaxWidth(),
            items = items,
            startIdx = startIdx,
            state = state,
            onBack = { onBack() },
            onChangeEdit = { editEnabled = !editEnabled },
            editEnabled = editEnabled
        )
        Spacer(modifier = Modifier.height(32.dp))
    }

    if (state.selectedItem == null) return

    onSelectItemValue(state.selectedItem!!)
}

@Composable
fun ItemDualNumberPicker(
    title: String,
    firstItems: List<String>,
    firstStartIdx: Int,
    secondItems: List<String>,
    secondStartIdx: Int,
    onSelectItemValue: (String) -> Unit,
    onBack: () -> Unit
) {
    val firstState = rememberPickerState<String>()
    val secondState = rememberPickerState<String>()

    var editEnabled by remember { mutableStateOf(false) }

    ItemTitleCard(title = title) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Picker(
                items = firstItems,
                startIdx = firstStartIdx,
                state = firstState,
                modifier = Modifier.weight(0.5f),
                textModifier = Modifier.padding(8.dp),
                onBack = { onBack() },
                onChangeEdit = { editEnabled = !editEnabled },
                editEnabled = editEnabled
            )

            Text(modifier = Modifier.width(8.dp), text = ".", fontSize = 24.sp)

            Picker(
                items = secondItems,
                startIdx = secondStartIdx,
                state = secondState,
                modifier = Modifier.weight(0.5f),
                textModifier = Modifier.padding(8.dp),
                onBack = { onBack() },
                onChangeEdit = { editEnabled = !editEnabled },
                editEnabled = editEnabled
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
    }
    onSelectItemValue("${firstState.selectedItem}.${secondState.selectedItem}")
}

@Composable
fun ItemDateTimePicker(
    title: String,
    currentTime: LocalDateTime,
    onSelectTime: (LocalDateTime) -> Unit
) {
    val dateState = rememberPickerState<String>()
    val hourState = rememberPickerState<Int>()
    val minState = rememberPickerState<String>()

    ItemTitleCard(title) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Picker(
                items = Constants.RANGE_DATE_LIST.map { it.format(Constants.DATE_FORMATTER_DETAIL) },
                startIdx = Constants.RANGE_DATE_LIST.indexOf(currentTime.toLocalDate()),
                state = dateState,
                modifier = Modifier.weight(0.5f),
                textModifier = Modifier.padding(8.dp),
                onBack = { },
                onChangeEdit = { },
            )

            Picker(
                items = Constants.RANGE_TIME_HOUR_LIST.map { it },
                startIdx = Constants.RANGE_TIME_HOUR_LIST.indexOf(currentTime.hour),
                state = hourState,
                modifier = Modifier.weight(0.25f),
                textModifier = Modifier
                    .padding(8.dp),
                onBack = { },
                onChangeEdit = { },
            )

            Text(modifier = Modifier.width(8.dp), text = ":", fontSize = 24.sp)

            Picker(
                items = Constants.RANGE_TIME_MIN_LIST.map { String.format("%02d", it) },
                startIdx = Constants.RANGE_TIME_MIN_LIST.indexOf(currentTime.minute),
                state = minState,
                modifier = Modifier.weight(0.25f),
                textModifier = Modifier
                    .padding(8.dp),
                onBack = { },
                onChangeEdit = { },
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }


//    LocalDate.parse(dateState.selectedItem!!, Constants.DATE_FORMATTER_DETAIL)
//        .atTime(
//            hourState.selectedItem!!,
//            minState.selectedItem!!.toInt(),
//            currentTime.second,
//            currentTime.nano
//        ).run {
//            onSelectTime(this)
//        }
}


@Preview(showBackground = true)
@Composable
fun PreviewNumberPicker() {
    Column(modifier = Modifier.fillMaxSize()) {
//        NumberPicker(
//            title = "혈당 (mg/dL)",
//            items = (40..300).map { it.toString() },
//            startIdx = 39,
//            onSelectItemValue = {},
//            onBack = {}
//        )


        Dialog(
            onDismissRequest = {},
            properties = DialogProperties(decorFitsSystemWindows = false)
        ) {
            ItemDateTimePicker(
                title = "",
                currentTime = LocalDateTime.now()
            ) {

            }
        }
    }
}