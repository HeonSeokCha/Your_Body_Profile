package com.chs.your_body_profile.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medicine_info")
data class MedicineInfoEntity(
    @PrimaryKey
    val insertTime: Long = System.currentTimeMillis(),
    val measureType: String,
    val title: String,
    val memo: String,
    val lastModifyTime: Long
)
