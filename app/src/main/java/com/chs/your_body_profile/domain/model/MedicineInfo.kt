package com.chs.your_body_profile.domain.model

import java.time.LocalDate

data class MedicineInfo(
    val measureTime: LocalDate,
    val medicineType: MedicineType,
    val title: String,
    val memo: String
)