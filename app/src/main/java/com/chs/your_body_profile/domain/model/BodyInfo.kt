package com.chs.your_body_profile.domain.model

data class BodyInfo(
    val weight: Float,
    val systolic: Int,
    val diastolic: Int,
    val bloodSugar: Int,
    val insulinVolume: Int,
    val desc: String?
)
