package com.chs.your_body_profile.presentation.screen.blood_pressure.input

import java.time.LocalDateTime

sealed class BloodPressureInputEvent {
    data object ChangeShowDateTimePicker : BloodPressureInputEvent()
    data class ChangeDateTime(val dateTime: LocalDateTime) : BloodPressureInputEvent()
    data class OnChangeMemo(val memo: String) : BloodPressureInputEvent()
    data class OnChangeDiastolic(val value: Int) : BloodPressureInputEvent()
    data class OnChangeSystolic(val value: Int) : BloodPressureInputEvent()
    data object OnClickSaveButton : BloodPressureInputEvent()
    data object OnBack : BloodPressureInputEvent()
}