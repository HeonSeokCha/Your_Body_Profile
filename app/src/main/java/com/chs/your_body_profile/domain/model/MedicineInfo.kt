package com.chs.your_body_profile.domain.model

import java.time.LocalDateTime

data class MedicineInfo(
    val doseTime: LocalDateTime,
    val medicineType: MedicineType,
    val title: String,
    val memo: String? = null
)