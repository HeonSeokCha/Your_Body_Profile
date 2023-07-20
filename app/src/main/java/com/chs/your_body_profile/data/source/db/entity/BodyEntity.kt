package com.chs.your_body_profile.data.source.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "body_info")
data class BodyInfoEntity(
    @PrimaryKey
    val regDate: Long,
    val systolic: Int,
    val diastolic: Int,
    val bloodSugar: Int,
    val insulinVolume: Int,
    val desc: String?
)
