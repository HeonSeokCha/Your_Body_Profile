package com.chs.your_body_profile.presentation.screen.blood_pressure.list

import com.chs.your_body_profile.domain.model.BloodPressureInfo

sealed class BloodPressureListEvent {
    data class OnChangeSelectIdx(val idx: Int) : BloodPressureListEvent()
    data class OnSelectInfo(val infoList: List<BloodPressureInfo>) : BloodPressureListEvent()
    data object OnClickInputButton : BloodPressureListEvent()
    data class OnLongClickItem(val info: BloodPressureInfo) : BloodPressureListEvent()
    data class OnClickItem(val info: BloodPressureInfo) : BloodPressureListEvent()
    data object OnChangeShowRemoveDialog : BloodPressureListEvent()
    data object OnChangeShowDetailDialog : BloodPressureListEvent()
    data object OnRemoveInfo : BloodPressureListEvent()
    data object OnBack : BloodPressureListEvent()
}