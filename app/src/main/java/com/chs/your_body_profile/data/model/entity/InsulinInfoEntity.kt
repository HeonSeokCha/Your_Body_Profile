package com.chs.your_body_profile.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chs.your_body_profile.common.toLocalDateToMillis

@Entity(tableName = "insulin_info")
data class InsulinInfoEntity(
    val insertDate: Long = System.currentTimeMillis().toLocalDateToMillis(),
    val level: Int,
    val memo: String?,
    val lastModifyTime: Long
)
