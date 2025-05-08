package com.chs.your_body_profile.presentation.screen.insulin

import java.time.LocalDateTime

data class InsulinInputState(
    val injectDateTime: LocalDateTime = LocalDateTime.now(),
    val level: Int = 0,
    val memo: String? = null,
    val isShowDateTimePicker: Boolean = false
)
