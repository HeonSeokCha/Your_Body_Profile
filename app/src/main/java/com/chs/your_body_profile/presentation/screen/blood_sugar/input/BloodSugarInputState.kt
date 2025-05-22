package com.chs.your_body_profile.presentation.screen.blood_sugar.input

import java.time.LocalDateTime

data class BloodSugarInputState(
    val measureDateTime: LocalDateTime = LocalDateTime.now(),
    val level: Int = 100,
    val selectedMeasureIdx: Int = 0,
    val memo: String? = null,
    val isShowDateTimePicker: Boolean = false
)
