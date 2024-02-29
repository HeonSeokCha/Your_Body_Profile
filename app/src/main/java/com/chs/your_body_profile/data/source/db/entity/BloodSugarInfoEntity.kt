package com.chs.your_body_profile.data.source.db.entity

import androidx.room.Entity

@Entity(
    tableName = "blood_sugar_info",
    primaryKeys = ["measureDate", "measureTime"]
)
data class BloodSugarInfoEntity(
    val measureDate: Long,
    val measureTime: Long,
    val measureType: Int,
    val number: Int,
    val memo: String?
)
