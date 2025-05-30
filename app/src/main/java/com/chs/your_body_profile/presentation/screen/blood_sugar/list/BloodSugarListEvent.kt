package com.chs.your_body_profile.presentation.screen.blood_sugar.list

import com.chs.your_body_profile.domain.model.BloodSugarInfo

sealed class BloodSugarListEvent {
    data class OnChangeSelectIdx(val idx: Int) : BloodSugarListEvent()
    data class OnSelectInfo(val infoList: List<BloodSugarInfo>) : BloodSugarListEvent()
    data object OnClickInputButton : BloodSugarListEvent()
    data object OnLongClickItem : BloodSugarListEvent()
    data object OnBack : BloodSugarListEvent()
}