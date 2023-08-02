package com.chs.your_body_profile.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "body_measure_list")
data class BodyMeasureInfoEntity(
    @PrimaryKey
    val title: String,
    val unit: String,
    val lastModifyTme: Long
)