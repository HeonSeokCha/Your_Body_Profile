package com.chs.your_body_profile.domain.model

import java.time.LocalDate
import java.time.LocalDateTime

data class WeightInfo(
    val measureDate: LocalDate,
    val measureTime: LocalDateTime,
    val weight: Float,
    val memo: String
)
