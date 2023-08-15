package com.chs.your_body_profile.domain.model

import java.time.LocalDate

data class HemoglobinA1cInfo(
    val measureTime: LocalDate,
    val number: Float,
    val measureHospital: String,
    val memo: String
)