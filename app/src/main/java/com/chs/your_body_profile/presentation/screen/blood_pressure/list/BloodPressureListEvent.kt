package com.chs.your_body_profile.presentation.screen.blood_pressure.list

import com.chs.your_body_profile.domain.model.BloodPressureInfo

sealed class BloodPressureListEvent {
    data class OnSelectInfo(val info: List<BloodPressureInfo>) : BloodPressureListEvent()
    data object OnClickInputButton : BloodPressureListEvent()
    data object OnLongClickItem : BloodPressureListEvent()
    data object OnBack : BloodPressureListEvent()
}