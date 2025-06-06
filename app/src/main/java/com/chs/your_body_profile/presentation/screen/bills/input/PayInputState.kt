package com.chs.your_body_profile.presentation.screen.bills.input

import java.time.LocalDateTime

data class PayInputState(
    val paymentTime: LocalDateTime = LocalDateTime.now(),
    val title: String = "",
    val subTitle: String? = null,
    val amount: Long = 10000,
    val memo: String? = null,
    val isShowDateTimePicker: Boolean = false
)