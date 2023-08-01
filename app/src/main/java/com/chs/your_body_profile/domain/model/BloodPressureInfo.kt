package com.chs.your_body_profile.domain.model

data class BloodPressureInfo(
    val measureTime: Long,
    val systolic: Int,
    val diastolic: Int,
    val useMedication: Boolean,
    val memo: String
)