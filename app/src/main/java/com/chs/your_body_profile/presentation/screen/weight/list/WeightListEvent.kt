package com.chs.your_body_profile.presentation.screen.weight.list

import com.chs.your_body_profile.domain.model.WeightInfo


sealed class WeightListEvent {
    data class OnSelectInfo(val idx: Int) : WeightListEvent()
    data object OnClickInputButton : WeightListEvent()
    data object OnLongClickItem : WeightListEvent()
    data object OnBack : WeightListEvent()
}