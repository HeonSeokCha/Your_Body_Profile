package com.chs.your_body_profile.presentation.common

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.chs.your_body_profile.common.Constants
import java.time.LocalDateTime

@Composable
fun ItemCurrentDateTime(
    currentDateTime: LocalDateTime,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() }
    ) {
        Text(
            text = currentDateTime.format(Constants.DATE_TIME_FORMATTER_DETAIL),
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp
        )
    }
}



@Composable
fun ItemTimePicker(
    dateTime: LocalDateTime,
    onClick: (LocalDateTime) -> Unit
) {
    val currentDateTime by remember { mutableStateOf(dateTime) }
    Button(
        onClick = { }
    ) {
        Text(
            text = currentDateTime.format(Constants.DATE_TIME_FORMATTER),

        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreViewInputBloodSugarScreen() {
    ItemCurrentDateTime(
        currentDateTime = LocalDateTime.now()
    ) {

    }
}
