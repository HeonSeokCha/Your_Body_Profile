package com.chs.your_body_profile.presentation.screen.insulin

import java.time.LocalDateTime

sealed class InsulinInputEvent {
    data object ChangeShowDateTimePicker : InsulinInputEvent()
    data class OnChangeDateTime(val dateTime: LocalDateTime) : InsulinInputEvent()
    data class OnChangeInsulinLevel(val level: Int): InsulinInputEvent()
    data class OnChangeMemo(val memo: String): InsulinInputEvent()
    data object OnBack : InsulinInputEvent()
}