package com.chs.your_body_profile.presentation.screen.food

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chs.your_body_profile.domain.model.FoodDetailInfo

@Composable
fun ItemSearchFoodInfo(
    info: FoodDetailInfo,
    onClick: (FoodDetailInfo) -> Unit,
    leadingContent: @Composable () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(info) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        leadingContent()

        Column {
            Text(text = info.name)

            Row {
                Text(text = "${info.calorie} kcal")
                Text(text = "(${info.servingWeight}) g")
            }
        }
    }
}