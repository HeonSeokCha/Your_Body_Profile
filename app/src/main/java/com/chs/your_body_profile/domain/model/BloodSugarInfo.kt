package com.chs.your_body_profile.domain.model

import java.time.LocalDateTime

data class BloodSugarInfo(
    val measureTime: LocalDateTime,
    val measureType: MeasureType,
    val number: Int,
    val memo: String?
)