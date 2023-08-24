package com.chs.your_body_profile.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chs.your_body_profile.common.toLocalDateToMillis

@Entity(
    tableName = "blood_pressure_info",
    primaryKeys = ["insertDate", "measureTime"]
)
data class BloodPressureInfoEntity(
    val insertDate: Long = System.currentTimeMillis().toLocalDateToMillis(),
    val measureTime: Long,
    val systolic: Int,
    val diastolic: Int,
    val memo: String,
    val lastModifyTime: Long
)
