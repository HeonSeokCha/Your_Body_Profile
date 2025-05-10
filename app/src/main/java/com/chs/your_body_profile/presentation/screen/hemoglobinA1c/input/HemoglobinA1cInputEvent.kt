package com.chs.your_body_profile.presentation.screen.hemoglobinA1c.input

import java.time.LocalDateTime

sealed class HemoglobinA1cInputEvent {
    data object ChangeShowDateTimePicker : HemoglobinA1cInputEvent()
    data class ChangeDateTime(val dateTime: LocalDateTime) : HemoglobinA1cInputEvent()
    data object OnBack : HemoglobinA1cInputEvent()
}