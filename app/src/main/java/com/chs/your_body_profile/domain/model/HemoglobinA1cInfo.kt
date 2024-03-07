package com.chs.your_body_profile.domain.model

import java.time.LocalDateTime

data class HemoglobinA1cInfo(
    val measureDate: LocalDateTime,
    val number: Float,
    val measureHospital: String,
    val memo: String?
)