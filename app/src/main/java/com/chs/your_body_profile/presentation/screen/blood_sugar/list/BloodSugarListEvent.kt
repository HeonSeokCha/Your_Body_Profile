package com.chs.your_body_profile.presentation.screen.blood_sugar.list

import com.chs.your_body_profile.domain.model.BloodSugarInfo

sealed class BloodSugarListEvent {
    data class OnChangeSelectIdx(val idx: Int) : BloodSugarListEvent()
    data class OnSelectInfo(val infoList: List<BloodSugarInfo>) : BloodSugarListEvent()
    data object OnClickInputButton : BloodSugarListEvent()
    data class OnLongClickItem(val info: BloodSugarInfo) : BloodSugarListEvent()
    data class OnClickItem(val info: BloodSugarInfo) : BloodSugarListEvent()
    data object OnChangeShowRemoveDialog : BloodSugarListEvent()
    data object OnChangeShowDetailDialog : BloodSugarListEvent()
    data object OnRemoveInfo : BloodSugarListEvent()
    data object OnBack : BloodSugarListEvent()
}