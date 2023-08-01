package com.chs.your_body_profile.domain.model

data class BloodSugarInfo(
    val measureTime: Long,
    val measureType: String,
    val number: Int,
    val memo: String
)