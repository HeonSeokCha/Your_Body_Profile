package com.chs.your_body_profile.domain.model

import java.time.LocalDateTime

data class WeightInfo(
    val measureDateTime: LocalDateTime,
    val weight: Float,
    val memo: String?
)
