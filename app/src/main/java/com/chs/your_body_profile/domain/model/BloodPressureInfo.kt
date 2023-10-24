package com.chs.your_body_profile.domain.model

import java.time.LocalDate
import java.time.LocalDateTime

data class BloodPressureInfo(
    val measureDate: LocalDate,
    val measureTime: LocalDateTime,
    val systolic: Int,
    val diastolic: Int,
    val memo: String?
)