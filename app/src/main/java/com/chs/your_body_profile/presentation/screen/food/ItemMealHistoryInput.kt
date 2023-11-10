package com.chs.your_body_profile.presentation.screen.food

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chs.your_body_profile.domain.model.FoodDetailInfo
import kotlin.math.roundToInt

@Composable
fun ItemMealHistoryInput(
    foodDetailInfo: FoodDetailInfo
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = foodDetailInfo.name,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        Row {
            Text(
                text = "${foodDetailInfo.calorie.roundToInt()}kcal, ",
                color = Color.Gray
            )

            Text(
                text = "${foodDetailInfo.servingWeight.roundToInt()}g",
                color = Color.Gray
            )
        }

        Divider(
            modifier = Modifier
                .padding(vertical = 8.dp),
            color = Color.Gray
        )
    }
}