package com.chs.your_body_profile.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.chs.your_body_profile.R

@Composable
fun ItemMeasureInfo(
    minimumInfo: Int,
    maximumInfo: Int,
    averageInfo: Int,
    measureUnit: String
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            ItemMeasureValue(
                title = "최소",
                value = minimumInfo,
                measureUnit = measureUnit
            )

            ItemMeasureValue(
                title = "최대",
                value = maximumInfo,
                measureUnit = measureUnit
            )
        }

        ItemMeasureValue(
            title = "평균",
            value = averageInfo,
            measureUnit = measureUnit
        )
    }
}

@Composable
fun ItemMeasureValue(
    title: String,
    value: Int,
    measureUnit: String
) {
    Row {
        Text(
            text = value.toString(),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Column {
            Text(text = title)
            Text(text = measureUnit)
        }
    }
}