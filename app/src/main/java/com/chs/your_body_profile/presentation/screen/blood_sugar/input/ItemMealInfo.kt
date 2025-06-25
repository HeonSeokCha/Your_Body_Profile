package com.chs.your_body_profile.presentation.screen.blood_sugar.input

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.chs.your_body_profile.domain.model.MealHistoryInfo

@Composable
fun ItemInputMealInfo(

    onAdd: (MealHistoryInfo) -> Unit
) {
    val mealInfo by remember { mutableStateOf(MealHistoryInfo()) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TextField(
            modifier = Modifier.weight(1f),
            value = mealInfo.mealName,
            placeholder = {
                Text(
                    text = "음식",
                    color = Color.Gray
                )
            },
            colors = TextFieldDefaults.colors(
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            onValueChange = {
            },
            trailingIcon = {
                IconButton(onClick = { onAdd(mealInfo) }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null
                    )
                }
            }
        )
    }
}

@Composable
fun ItemMealInfo(
    info: MealHistoryInfo,
    onRemove: (MealHistoryInfo) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = info.mealName)

        IconButton(onClick = { onRemove(info) }) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = null
            )
        }
    }
}