package com.chs.your_body_profile.presentation.screen.blood_sugar

import java.time.LocalDateTime

sealed class BloodSugarInputEvent {
    data object ChangeShowDateTimePicker : BloodSugarInputEvent()
    data class ChangeDateTime(val dateTime: LocalDateTime) : BloodSugarInputEvent()
    data object OnBack : BloodSugarInputEvent()
}