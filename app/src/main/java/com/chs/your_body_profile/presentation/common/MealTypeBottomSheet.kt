package com.chs.your_body_profile.presentation.common

import androidx.compose.runtime.Composable
import com.chs.your_body_profile.domain.model.MealType

@Composable
fun MealTypeBottomSheet(
    onClick: (String) -> Unit
) {
    ItemMeasureTypeVerticalList(
        title = "식사종류",
        items = MealType.values().map { it.mean.second }
    ) {
        onClick(it)
    }
}