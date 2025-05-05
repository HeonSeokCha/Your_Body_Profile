package com.chs.your_body_profile.presentation.common

import androidx.compose.runtime.Composable
import com.chs.your_body_profile.domain.model.MealType

@Composable
fun ItemMealTypeAlertDialog(
    onDisMiss: (Boolean) -> Unit,
    onClick: (String) -> Unit
) {
    ItemVerticalListAlertDialog(
        title = "식사종류",
        items = MealType.entries.map { it.mean.second },
        onDisMiss = { onDisMiss(it) }
    ) {
        onClick(it)
    }
}