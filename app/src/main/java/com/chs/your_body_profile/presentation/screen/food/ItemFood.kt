package com.chs.your_body_profile.presentation.screen.food

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chs.your_body_profile.domain.model.FoodDetailInfo

@Composable
fun ItemSelectFood(
    name: String,
    onClick: (String) -> Unit
) {
    SuggestionChip(
        modifier = Modifier
            .height(24.dp)
            .padding(
                horizontal = 2.dp),
        onClick = { onClick(name) },
        label = {
            Text(
                text = name,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        },
        icon = {
            Icon(imageVector = Icons.Default.Close, contentDescription = null)
        },
        shape = RoundedCornerShape(16.dp)
    )
}

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
            Text(
                text = info.name,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Row {
                Text(
                    text = "${info.calorie} kcal",
                    color = Color.Gray
                )
                Text(
                    text = "(${info.servingWeight}g)",
                    color = Color.Gray
                )

            }
        }
    }
}
