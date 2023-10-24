package com.chs.your_body_profile.presentation.screen.blood_pressure

import java.time.LocalDate
import java.time.LocalDateTime

data class BloodPressureInputState(
    val measureDate: LocalDate = LocalDate.now(),
    val measureTime: LocalDateTime = LocalDateTime.now(),
    val systolic: Int = 0,
    val diastolic: Int = 0,
    val memo: String? = null
)
