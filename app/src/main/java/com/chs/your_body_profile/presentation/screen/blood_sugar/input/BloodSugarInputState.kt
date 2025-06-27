package com.chs.your_body_profile.presentation.screen.blood_sugar.input

import com.chs.your_body_profile.domain.model.MealType
import com.chs.your_body_profile.domain.model.MeasureType
import java.time.LocalDateTime

data class BloodSugarInputState(
    val measureDateTime: LocalDateTime = LocalDateTime.now(),
    val level: Int = 100,
    val selectedMeasureIdx: MeasureType = MeasureType.BEFORE_EAT,
    val selectMealTypeIdx: MealType = MealType.MORNING,
    val memo: String? = null,
    val mealText: String = "",
    val mealList: List<String> = emptyList(),
    val isShowDateTimePicker: Boolean = false
)
