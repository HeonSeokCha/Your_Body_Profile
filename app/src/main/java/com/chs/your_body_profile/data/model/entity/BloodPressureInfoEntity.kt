package com.chs.your_body_profile.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "blood_pressure_info")
data class BloodPressureInfoEntity(
    @PrimaryKey
    val insertTime: Long = System.currentTimeMillis(),
    val measureTime: Long,
    val systolic: Int,
    val diastolic: Int,
    val useMedication: Boolean,
    val memo: String,
    val lastModifyTime: Long
)
