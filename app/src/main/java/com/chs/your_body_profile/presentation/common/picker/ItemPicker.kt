package com.chs.your_body_profile.presentation.common.picker

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.presentation.common.ItemTitleCard
import java.time.LocalDate
import java.time.LocalDateTime

@Composable
fun <T> ItemPicker(
    title: String,
    items: List<T>,
    startIdx: Int,
    onSelectItemValue: (T) -> Unit,
) {
    ItemTitleCard(title = title) {
        Picker(
            items = items,
            startIdx = startIdx,
            onValueChange = { onSelectItemValue(it) }
        )
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun ItemDualNumberPicker(
    title: String,
    firstItems: List<String>,
    firstStartIdx: Int,
    secondItems: List<String>,
    secondStartIdx: Int,
    onSelectItemValue: (String) -> Unit,
) {
    var firstItem by remember { mutableStateOf(firstItems[firstStartIdx]) }
    var secondItem by remember { mutableStateOf(secondItems[secondStartIdx]) }

    LaunchedEffect(firstItem, secondItem) {
        onSelectItemValue("$firstItem.$secondItem")
    }

    ItemTitleCard(title = title) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Spacer(modifier = Modifier.weight(0.25f))
            Picker(
                modifier = Modifier.weight(0.3f),
                items = firstItems,
                startIdx = firstStartIdx,
                onValueChange = { firstItem = it }
            )

            Text(
                text = ".",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )

            Picker(
                modifier = Modifier.weight(0.3f),
                items = secondItems,
                startIdx = secondStartIdx,
                onValueChange = { secondItem = it }
            )
            Spacer(modifier = Modifier.weight(0.25f))
        }
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun ItemDateTimePicker(
    title: String,
    currentTime: LocalDateTime,
    onSelectTime: (LocalDateTime) -> Unit
) {
    var dateState by remember { mutableStateOf(currentTime.format(Constants.DATE_FORMATTER_DETAIL)) }
    var hourState by remember { mutableStateOf(String.format("%02d", currentTime.hour)) }
    var minState by remember { mutableStateOf(String.format("%02d", currentTime.minute)) }

//    LaunchedEffect(dateState, hourState, minState) {
//        LocalDate.parse(dateState, Constants.DATE_FORMATTER_DETAIL)
//            .atTime(
//                hourState,
//                minState.toInt(),
//                currentTime.second,
//                currentTime.nano
//            ).run {
//                onSelectTime(this)
//            }
//    }

    ItemTitleCard(title) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Picker(
                modifier = Modifier.weight(0.5f),
                items = Constants.RANGE_DATE_LIST.map { it.format(Constants.DATE_FORMATTER_DETAIL) },
                startIdx = Constants.RANGE_DATE_LIST.indexOf(currentTime.toLocalDate()),
                infiniteScroll = false,
                onValueChange = { dateState = it }
            )

            Picker(
                modifier = Modifier.weight(0.25f),
                items = Constants.RANGE_TIME_HOUR_LIST.map { String.format("%02d", it) },
                startIdx = Constants.RANGE_TIME_HOUR_LIST.indexOf(currentTime.hour),
                onValueChange = { hourState = it }
            )

            Text(modifier = Modifier.width(8.dp), text = ":", fontSize = 24.sp)


            Picker(
                modifier = Modifier.weight(0.25f),
                items = Constants.RANGE_TIME_MIN_LIST.map { String.format("%02d", it) },
                startIdx = Constants.RANGE_TIME_MIN_LIST.indexOf(currentTime.minute),
                onValueChange = { minState = it }
            )


        }
    }
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