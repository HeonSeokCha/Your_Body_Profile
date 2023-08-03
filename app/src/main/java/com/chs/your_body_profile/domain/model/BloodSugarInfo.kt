package com.chs.your_body_profile.domain.model

data class BloodSugarInfo(
    val measureTime: Long,
    val measureType: MeasureType,
    val number: Int,
    val memo: String
)