package com.chs.your_body_profile.domain.model

import java.time.LocalDate

data class WeightInfo(
    val measureTime: LocalDate,
    val weight: Float,
    val memo: String
)
