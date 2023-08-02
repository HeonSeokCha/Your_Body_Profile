package com.chs.your_body_profile.presentation.home

import com.chs.your_body_profile.domain.model.BodyMeasureInfo

data class HomeState(
    val bodyMeasureList: List<BodyMeasureInfo> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)