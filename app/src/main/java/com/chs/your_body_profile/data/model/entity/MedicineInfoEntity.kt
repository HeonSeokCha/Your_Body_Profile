package com.chs.your_body_profile.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chs.your_body_profile.common.toLocalDateToMillis

@Entity(
    tableName = "medicine_info",
    primaryKeys = ["takenDate", "takeMedicineType", "title"]
)
data class MedicineInfoEntity(
    val takenDate: Long,
    val takenTime: Long,
    val takeMedicineType: Int,
    val title: String,
    val memo: String? = null
)
