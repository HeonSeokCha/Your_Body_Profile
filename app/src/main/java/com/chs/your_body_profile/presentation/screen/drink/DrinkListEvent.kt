package com.chs.your_body_profile.presentation.screen.drink

import com.chs.your_body_profile.domain.model.DrinkInfo


sealed class DrinkListEvent {
    data class OnChangeSelectIdx(val idx: Int) : DrinkListEvent()
    data class OnSelectInfo(val info: List<DrinkInfo>) : DrinkListEvent()
    data object OnLongClickItem : DrinkListEvent()
    data object OnBack : DrinkListEvent()
}