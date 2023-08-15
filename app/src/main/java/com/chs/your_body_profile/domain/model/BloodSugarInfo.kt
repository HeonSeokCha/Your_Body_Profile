package com.chs.your_body_profile.domain.model

import java.time.LocalDate

data class BloodSugarInfo(
    val measureTime: LocalDate,
    val measureType: MeasureType,
    val number: Int,
    val memo: String
)