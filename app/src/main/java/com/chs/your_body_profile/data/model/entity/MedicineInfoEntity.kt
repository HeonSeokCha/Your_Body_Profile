package com.chs.your_body_profile.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chs.your_body_profile.common.toLocalDateToMillis

@Entity(
    tableName = "medicine_info",
    primaryKeys = ["insertDate", "title"]
)
data class MedicineInfoEntity(
    val insertDate: Long = System.currentTimeMillis().toLocalDateToMillis(),
    val doseTime: Long,
    val title: String,
    val takeMedicineType: String,
    val memo: String? = null,
    val lastModifyTime: Long
)
