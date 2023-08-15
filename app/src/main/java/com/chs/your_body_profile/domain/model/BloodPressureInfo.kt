package com.chs.your_body_profile.domain.model

import java.time.LocalDate

data class BloodPressureInfo(
    val measureTime: LocalDate,
    val systolic: Int,
    val diastolic: Int,
    val useMedication: Boolean,
    val memo: String
)