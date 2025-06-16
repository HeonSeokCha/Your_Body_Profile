package com.chs.your_body_profile.presentation.screen.hemoglobinA1c.list

import com.chs.your_body_profile.domain.model.HemoglobinA1cInfo

sealed class HemoglobinA1cListEvent {
    data class OnChangeSelectIdx(val idx: Int) : HemoglobinA1cListEvent()
    data class OnSelectInfo(val infoList: List<HemoglobinA1cInfo>) : HemoglobinA1cListEvent()
    data object OnClickInputButton : HemoglobinA1cListEvent()
    data class OnLongClickItem(val info: HemoglobinA1cInfo): HemoglobinA1cListEvent()
    data object OnChangeShowDialog : HemoglobinA1cListEvent()
    data object OnRemoveInfo : HemoglobinA1cListEvent()
    data object OnBack : HemoglobinA1cListEvent()
}