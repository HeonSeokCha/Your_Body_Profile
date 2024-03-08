package com.chs.your_body_profile.data.source.db.entity

import androidx.room.Entity

@Entity(
    tableName = "medicine_info",
    primaryKeys = ["takenDateTime", "takeMedicineType", "title"]
)
data class MedicineInfoEntity(
    val takenDateTime: Long,
    val takeMedicineType: Int,
    val title: String,
    val memo: String? = null
)
