package com.chs.your_body_profile.presentation.screen.food

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.chs.your_body_profile.domain.model.FoodDetailInfo

@Composable
fun ItemSearchFoodInfo(
    info: FoodDetailInfo
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = info.name
        )

        Row {
            Text(text = "${info.calorie} kcal")
            Text(text = "(${info.servingWeight}) g")
        }
    }
}