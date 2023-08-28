package com.chs.your_body_profile.presentation.screen.blood_pressure

import java.time.LocalDateTime

data class BloodPressureInputState(
    val measureTime: LocalDateTime? = null,
    val systolic: Int = 0,
    val diastolic: Int = 0,
    val memo: String? = null
)
