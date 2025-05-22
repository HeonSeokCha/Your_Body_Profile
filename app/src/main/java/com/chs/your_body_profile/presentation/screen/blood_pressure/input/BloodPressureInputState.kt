package com.chs.your_body_profile.presentation.screen.blood_pressure.input

import java.time.LocalDateTime

data class BloodPressureInputState(
    val measureDateTime: LocalDateTime = LocalDateTime.now(),
    val systolic: Int = 120,
    val diastolic: Int = 80,
    val memo: String? = null,
    val isShowDateTimePicker: Boolean = false
)
