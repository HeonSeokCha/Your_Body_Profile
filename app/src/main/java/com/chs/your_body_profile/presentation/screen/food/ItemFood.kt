package com.chs.your_body_profile.presentation.screen.food

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chs.your_body_profile.R
import com.chs.your_body_profile.domain.model.FoodDetailInfo
import com.chs.your_body_profile.presentation.common.shimmerEffect

@Composable
fun ItemSelectFood(
    name: String,
    onClick: (String) -> Unit
) {
    SuggestionChip(
        modifier = Modifier
            .padding(horizontal = 2.dp),
        onClick = { onClick(name) },
        label = {
            Text(
                text = name,
                fontSize = 22.sp,
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
    info: FoodDetailInfo?,
    onClick: (FoodDetailInfo) -> Unit,
    leadingContent: @Composable () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                if (info != null) {
                    onClick(info)
                }
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (info == null) {
            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .size(24.dp)
                        .clip(CircleShape)
                        .shimmerEffect()
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(20.dp)
                            .shimmerEffect()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                            .height(20.dp)
                            .shimmerEffect()
                    )
                }
            }
        } else {
            leadingContent()

            Column {
                Text(
                    text = info.name,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Row {
                    Text(
                        text = "${info.calorie} kcal",
                        color = Color.Gray
                    )

                    Spacer(Modifier.width(8.dp))

                    Text(
                        text = "(${info.servingWeight ?: 0}g)",
                        color = Color.Gray
                    )
                }
            }
        }
    }
}
