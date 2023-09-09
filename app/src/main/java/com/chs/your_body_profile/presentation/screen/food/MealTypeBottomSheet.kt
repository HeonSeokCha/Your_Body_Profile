package com.chs.your_body_profile.presentation.screen.food

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.chs.your_body_profile.domain.model.MealType
import com.chs.your_body_profile.presentation.common.ItemMeasureTypeVerticalList

@Composable
fun MealTypeBottomSheet(
    navController: NavHostController
) {
    ItemMeasureTypeVerticalList(
        title = "식사종류",
        items = MealType.values().map { it.mean.second }
    ) {
    }
}