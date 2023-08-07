package com.chs.your_body_profile.data.model.entity

import androidx.room.Entity

@Entity(
    tableName = "body_summary_info",
    primaryKeys = [
        "insertDate",
        "type"
    ]
)
data class BodySummaryInfoEntity(
    val insertDate: Long = 0L,
    val type: String,
    val todayLastInfo: String,
    val measureUnit: String,
    val lastModifyTime: Long
)
