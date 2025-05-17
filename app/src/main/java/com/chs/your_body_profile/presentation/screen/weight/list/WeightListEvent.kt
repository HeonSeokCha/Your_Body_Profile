package com.chs.your_body_profile.presentation.screen.weight.list

import com.chs.your_body_profile.domain.model.WeightInfo


sealed class WeightListEvent {
    data class OnSelectInfo(val info: List<WeightInfo>) : WeightListEvent()
    data object OnClickInputButton : WeightListEvent()
    data object OnLongClickItem : WeightListEvent()
    data object OnBack : WeightListEvent()
}