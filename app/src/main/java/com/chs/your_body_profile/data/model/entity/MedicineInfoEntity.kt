package com.chs.your_body_profile.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medicine_info")
data class MedicineInfoEntity(
    @PrimaryKey
    val insertDate: Long = 0L,
    val title: String,
    val takeMedicineType: String,
    val memo: String,
    val lastModifyTime: Long
)
