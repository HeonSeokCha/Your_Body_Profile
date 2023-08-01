package com.chs.your_body_profile.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "blood_sugar_info")
data class BloodSugarInfoEntity(
    @PrimaryKey
    val insertTime: Long = System.currentTimeMillis(),
    val measureType: String,
    val measureTime: Long,
    val number: Int,
    val memo: String,
    val lastModifyTime: Long
)
