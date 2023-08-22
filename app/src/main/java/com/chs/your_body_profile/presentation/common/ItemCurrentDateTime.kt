package com.chs.your_body_profile.presentation.common

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.chs.your_body_profile.common.Constants
import java.time.LocalDateTime

@Composable
fun ItemCurrentDateTime(
    onClick: (LocalDateTime) -> Unit
) {
    val currentDateTime = remember { LocalDateTime.now() }

    Button(
        onClick = { onClick(currentDateTime) }
    ) {
        Text(
            text = currentDateTime.format(Constants.DATE_TIME_FORMATTER_DETAIL)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreViewInputBloodSugarScreen() {
    ItemCurrentDateTime() {

    }
}
