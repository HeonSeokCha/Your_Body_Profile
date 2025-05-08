package com.chs.your_body_profile.presentation.screen.hemoglobinA1c

import java.time.LocalDateTime

data class HemoglobinA1cInputState(
    val measureDateTime: LocalDateTime = LocalDateTime.now(),
    val number: Float = 0.0f,
    val measureHospital: String? = null,
    val memo: String? = null,
    val isShowDateTimePicker: Boolean = false
)
