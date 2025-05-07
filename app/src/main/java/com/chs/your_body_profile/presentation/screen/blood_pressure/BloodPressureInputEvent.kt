package com.chs.your_body_profile.presentation.screen.blood_pressure

import java.time.LocalDateTime

sealed class BloodPressureInputEvent {
    data object ChangeShowDateTimePicker : BloodPressureInputEvent()
    data class ChangeDateTime(val dateTime: LocalDateTime) : BloodPressureInputEvent()
    data object OnBack : BloodPressureInputEvent()
}