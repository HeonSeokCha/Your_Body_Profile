package com.chs.your_body_profile.presentation.screen.blood_sugar.input

import com.chs.your_body_profile.domain.model.MeasureType
import java.time.LocalDateTime

data class BloodSugarInputState(
    val measureDateTime: LocalDateTime = LocalDateTime.now(),
    val level: Int = 0,
    val selectedMeasureIdx: Int = 0,
    val memo: String? = null,
    val isShowDateTimePicker: Boolean = false
)
