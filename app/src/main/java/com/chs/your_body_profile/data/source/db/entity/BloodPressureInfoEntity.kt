package com.chs.your_body_profile.data.source.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "blood_pressure_info")
data class BloodPressureInfoEntity(
    @PrimaryKey
    val insertTime: Long = System.currentTimeMillis(),
    val systolic: Int,
    val diastolic: Int,
    val useMedication: Boolean,
    val lastModifyTime: Long
)
