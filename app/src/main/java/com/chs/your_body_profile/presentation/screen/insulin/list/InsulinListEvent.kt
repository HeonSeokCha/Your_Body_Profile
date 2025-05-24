package com.chs.your_body_profile.presentation.screen.insulin.list

import com.chs.your_body_profile.domain.model.InsulinInfo

sealed class InsulinListEvent {
    data class OnSelectInfo(val idx: Int) : InsulinListEvent()
    data object OnClickInputButton : InsulinListEvent()
    data object OnLongClickItem : InsulinListEvent()
    data object OnBack : InsulinListEvent()
}