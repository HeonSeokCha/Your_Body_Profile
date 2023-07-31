package com.chs.your_body_profile.data.source.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "insulin_info")
data class InsulinInfoEntity(
    @PrimaryKey
    val insertTime: Long = System.currentTimeMillis(),
    val injectTime: Long,
    val level: Int,
    val memo: String,
    val lastModifyTime: Long
)
