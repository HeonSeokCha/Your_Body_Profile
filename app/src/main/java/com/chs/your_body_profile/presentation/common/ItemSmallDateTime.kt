package com.chs.your_body_profile.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.time.LocalDate

@Composable
fun ItemSmallDateTime(
    date: LocalDate,
    currentDate: LocalDate,
    onClick: () -> Unit
) {
    Text(
        modifier = Modifier
            .clickable {
                onClick()
            }
            .drawBehind {
                drawRoundRect(
                    color = if (currentDate == date) Color.LightGray else Color.Transparent,
                    cornerRadius = CornerRadius(
                        15.dp.toPx(),
                        15.dp.toPx()
                    )
                )
            }
            .padding(
                horizontal = 12.dp,
                vertical = 8.dp
            ),
        text = if (date.dayOfMonth == 1) {
            "${date.monthValue} / ${date.dayOfMonth}"
        } else {
            date.dayOfMonth.toString()
        },
        color = Color.Black
    )
}