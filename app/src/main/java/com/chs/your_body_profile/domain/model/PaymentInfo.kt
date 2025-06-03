package com.chs.your_body_profile.domain.model

import java.time.LocalDateTime

data class PaymentInfo(
    val paymentTime: LocalDateTime,
    val title: String,
    val subTitle: String?,
    val amount: Long,
    val memo: String?
)
