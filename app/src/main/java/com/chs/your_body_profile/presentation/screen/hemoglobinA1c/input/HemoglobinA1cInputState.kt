package com.chs.your_body_profile.presentation.screen.hemoglobinA1c.input

import java.time.LocalDateTime

data class HemoglobinA1cInputState(
    val measureDateTime: LocalDateTime = LocalDateTime.now(),
    val number: Float = 5.5f,
    val measureHospital: String? = null,
    val memo: String? = null,
    val isShowDateTimePicker: Boolean = false
)
