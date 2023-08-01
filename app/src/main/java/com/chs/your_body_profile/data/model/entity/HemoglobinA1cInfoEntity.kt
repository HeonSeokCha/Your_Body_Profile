package com.chs.your_body_profile.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hemoglobin_a1c_info")
data class HemoglobinA1cInfoEntity(
    @PrimaryKey
    val insertTime: Long = System.currentTimeMillis(),
    val number: Float,
    val measureHospital: String,
    val memo: String,
    val lastModifyTime: Long
)
