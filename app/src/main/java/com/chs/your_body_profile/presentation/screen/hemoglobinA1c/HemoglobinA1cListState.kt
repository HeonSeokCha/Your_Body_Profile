package com.chs.your_body_profile.presentation.screen.hemoglobinA1c

import com.chs.your_body_profile.domain.model.HemoglobinA1cInfo

data class HemoglobinA1cListState(
    val list: List<HemoglobinA1cInfo> = emptyList(),
    val selectDeleteList: List<HemoglobinA1cInfo> = emptyList()
)
