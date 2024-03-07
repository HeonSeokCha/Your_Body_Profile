package com.chs.your_body_profile.data.source.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "blood_sugar_info",
)
data class BloodSugarInfoEntity(
    @PrimaryKey
    val measureDateTime: Long,
    val measureType: Int,
    val number: Int,
    val memo: String?
)
