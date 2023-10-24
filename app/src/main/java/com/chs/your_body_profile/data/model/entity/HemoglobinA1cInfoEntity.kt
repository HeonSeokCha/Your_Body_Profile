package com.chs.your_body_profile.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chs.your_body_profile.common.toLocalDateToMillis

@Entity(tableName = "hemoglobin_a1c_info")
data class HemoglobinA1cInfoEntity(
    @PrimaryKey
    val measureDate: Long,
    val number: Float,
    val measureHospital: String,
    val memo: String?
)
