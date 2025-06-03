package com.chs.your_body_profile.data.source.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pay_info")
data class PayInfoEntity(
    @PrimaryKey
    val paymentTime: Long,
    val title: String,
    val subTitle: String?,
    val amount: Long,
    val memo: String?
)
