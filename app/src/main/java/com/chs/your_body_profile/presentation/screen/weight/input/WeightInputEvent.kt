package com.chs.your_body_profile.presentation.screen.weight.input

import java.time.LocalDateTime

sealed class WeightInputEvent {
    data object ChangeShowDateTimePicker : WeightInputEvent ()
    data class ChangeDateTime(val dateTime: LocalDateTime) : WeightInputEvent ()
    data class OnChangeWeight(val weight: Float): WeightInputEvent ()
    data class OnChangeMemo(val memo: String): WeightInputEvent ()
    data object OnClickSaveButton : WeightInputEvent ()
    data object OnBack : WeightInputEvent ()
}