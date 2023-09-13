package com.chs.your_body_profile.presentation.screen.food

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun FoodAppBar(
    navController: NavHostController,
    mealType: String,
    selectCount: Int = 0
) {
    Text(
        text = if (selectCount == 0) {
            mealType
        } else {
            "${selectCount}개 선택됨"
        },
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )
}