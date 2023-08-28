package com.chs.your_body_profile.domain.model

import java.time.LocalDateTime

data class BloodPressureInfo(
    val measureTime: LocalDateTime,
    val systolic: Int,
    val diastolic: Int,
    val memo: String?
)