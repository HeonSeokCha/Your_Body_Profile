package com.chs.your_body_profile.data.source.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hemoglobin_a1c_info")
data class HemoglobinA1cInfoEntity(
    @PrimaryKey
    val measureDateTime: Long,
    val number: Float,
    val measureHospital: String,
    val memo: String?
)
