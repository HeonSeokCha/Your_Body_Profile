package com.chs.your_body_profile.presentation.screen.blood_sugar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.presentation.common.ItemCurrentDateTime
import com.chs.your_body_profile.presentation.common.NumberPicker
import java.time.LocalDateTime

@Composable
fun InputBloodSugarScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 8.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ItemCurrentDateTime {

        }

        Card {
            NumberPicker(
                title = "혈당 (mg/dL)",
                items = Constants.RANGE_BLOOD_SUGAR_NUMBER.map { it.toString() }
            ) { number ->

            }
        }
    }
}


