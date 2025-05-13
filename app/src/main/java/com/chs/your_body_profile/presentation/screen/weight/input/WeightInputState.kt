package com.chs.your_body_profile.presentation.screen.weight.input

import java.time.LocalDateTime

data class WeightInputState(
    val measureDateTime: LocalDateTime = LocalDateTime.now(),
    val weight: Float = 0.0f,
    val memo: String? = null,
    val isShowDateTimePicker: Boolean = false
)