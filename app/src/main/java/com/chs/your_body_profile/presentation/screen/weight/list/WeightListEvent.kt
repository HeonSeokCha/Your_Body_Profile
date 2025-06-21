package com.chs.your_body_profile.presentation.screen.weight.list

import com.chs.your_body_profile.domain.model.WeightInfo


sealed class WeightListEvent {
    data class OnChangeSelectIdx(val idx: Int) : WeightListEvent()
    data class OnSelectInfo(val info: List<WeightInfo>) : WeightListEvent()
    data object OnClickInputButton : WeightListEvent()
    data class OnLongClickItem(val info: WeightInfo): WeightListEvent()
    data class OnClickItem(val info: WeightInfo) : WeightListEvent()
    data object OnChangeShowRemoveDialog : WeightListEvent()
    data object OnChangeShowDetailDialog : WeightListEvent()
    data object OnRemoveInfo : WeightListEvent()
    data object OnBack : WeightListEvent()
}