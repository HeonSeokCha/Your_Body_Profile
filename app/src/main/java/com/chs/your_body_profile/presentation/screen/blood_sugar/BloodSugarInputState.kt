package com.chs.your_body_profile.presentation.screen.blood_sugar

import com.chs.your_body_profile.domain.model.MeasureType
import java.time.LocalDateTime

data class BloodSugarInputState(
    val measureTime: LocalDateTime? = null,
    val level: Int = 0,
    val measureType: MeasureType? = null,
    val memo: String? = null,
)