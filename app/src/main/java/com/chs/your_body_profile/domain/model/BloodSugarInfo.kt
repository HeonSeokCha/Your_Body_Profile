package com.chs.your_body_profile.domain.model

import java.time.LocalDateTime

data class BloodSugarInfo(
    val measureDateTime: LocalDateTime,
    val measureTypeIdx: MeasureType,
    val number: Int,
    val memo: String?,
    val mealInfo: List<MealHistoryInfo>
)