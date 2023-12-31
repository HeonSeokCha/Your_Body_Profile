package com.chs.your_body_profile.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chs.your_body_profile.common.toLocalDateToMillis

@Entity(tableName = "insulin_info")
data class InsulinInfoEntity(
    @PrimaryKey
    val injectDate: Long,
    val level: Int,
    val memo: String?
)
