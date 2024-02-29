package com.chs.your_body_profile.data.source.db.entity

import androidx.room.Entity

@Entity(
    tableName = "blood_pressure_info",
    primaryKeys = ["measureDate", "measureTime"]
)
data class BloodPressureInfoEntity(
    val measureDate: Long,
    val measureTime: Long,
    val systolic: Int,
    val diastolic: Int,
    val memo: String?
)
