package com.chs.your_body_profile.presentation.common

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.chs.your_body_profile.common.Constants
import java.time.LocalDateTime

@Composable
fun ItemCurrentDateTime(
    onClick: (LocalDateTime) -> Unit
) {
    val currentDateTime = remember { LocalDateTime.now() }

    LaunchedEffect(Unit) {
        onClick(currentDateTime)
    }

    Button(
        onClick = { }
    ) {
        Text(
            text = currentDateTime.format(Constants.DATE_TIME_FORMATTER_DETAIL)
        )
    }
}



@Composable
fun ItemTimePicker(
    dateTime: LocalDateTime,
    onClick: (LocalDateTime) -> Unit
) {
    var currentDateTime by remember { mutableStateOf(dateTime) }
    Button(
        onClick = { }
    ) {
        Text(
            text = currentDateTime.format(Constants.DATE_TIME_FORMATTER)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreViewInputBloodSugarScreen() {
    ItemCurrentDateTime() {

    }
}
