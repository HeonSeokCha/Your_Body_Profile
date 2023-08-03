package com.chs.your_body_profile.domain.model

data class MedicineInfo(
    val medicineType: MedicineType,
    val title: String,
    val memo: String
)