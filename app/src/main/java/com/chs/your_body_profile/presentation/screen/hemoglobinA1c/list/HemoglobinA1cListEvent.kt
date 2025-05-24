package com.chs.your_body_profile.presentation.screen.hemoglobinA1c.list

import com.chs.your_body_profile.domain.model.HemoglobinA1cInfo

sealed class HemoglobinA1cListEvent {
    data class OnSelectInfo(val idx: Int) : HemoglobinA1cListEvent()
    data object OnClickInputButton : HemoglobinA1cListEvent()
    data object OnLongClickItem : HemoglobinA1cListEvent()
    data object OnBack : HemoglobinA1cListEvent()
}