package com.chs.your_body_profile.data.source.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "blood_sugar_info")
data class BloodSugarInfoEntity(
    @PrimaryKey
    val insertTime: Long = System.currentTimeMillis(),
    val measureType: String,
    val level: Int,
    val hbA1c: Float,
    val memo: String,
    val lastModifyTime: Long
)
