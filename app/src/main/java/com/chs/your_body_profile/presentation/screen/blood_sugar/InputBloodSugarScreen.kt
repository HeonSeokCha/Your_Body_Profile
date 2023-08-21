package com.chs.your_body_profile.presentation.screen.blood_sugar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import com.chs.your_body_profile.presentation.common.ItemMeasureTypeList
import com.chs.your_body_profile.presentation.common.ItemSmallInputText
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

        NumberPicker(
            title = "혈당 (mg/dL)",
            items = Constants.RANGE_BLOOD_SUGAR_NUMBER.map { it.toString() }
        ) { number ->

        }

        Spacer(modifier = Modifier.height(32.dp))
        
        ItemMeasureTypeList(
            title = "현재 상태 선택",
            items = Constants.BLOOD_SUGAR_MEASURE_TYPE_LIST
        ) {

        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        ItemSmallInputText(onChangedText = { })
    }
}


