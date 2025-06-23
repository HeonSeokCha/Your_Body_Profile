package com.chs.your_body_profile.presentation.screen.blood_sugar.input

import com.chs.your_body_profile.domain.model.MealHistoryInfo
import java.time.LocalDateTime

sealed class BloodSugarInputEvent {
    data object ChangeShowDateTimePicker : BloodSugarInputEvent()
    data class ChangeDateTime(val dateTime: LocalDateTime) : BloodSugarInputEvent()
    data class OnChangeMemo(val memo: String) : BloodSugarInputEvent()
    data class OnChangeMeasureType(val idx: Int) : BloodSugarInputEvent()
    data class OnChangeBloodSugarLevel(val level: Int): BloodSugarInputEvent()
    data class AddMealInfo(val info: MealHistoryInfo) : BloodSugarInputEvent()
    data class RemoveMealInfo(val info: MealHistoryInfo) : BloodSugarInputEvent()
    data object OnClickSaveButton : BloodSugarInputEvent()
    data object OnBack : BloodSugarInputEvent()
}