package com.chs.your_body_profile.presentation.screen.insulin.list

import com.chs.your_body_profile.domain.model.InsulinInfo

sealed class InsulinListEvent {
    data class OnChangeSelectIdx(val idx: Int) : InsulinListEvent()
    data class OnSelectInfo(val infoList: List<InsulinInfo>) : InsulinListEvent()
    data object OnClickInputButton : InsulinListEvent()
    data class OnLongClickItem(val info: InsulinInfo): InsulinListEvent()
    data object OnChangeShowDialog : InsulinListEvent()
    data object OnRemoveInfo : InsulinListEvent()
    data object OnBack : InsulinListEvent()
}