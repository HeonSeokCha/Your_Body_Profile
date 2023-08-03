package com.chs.your_body_profile.presentation.body_dash_board

data class BodyDashBoardState(
    val bodyMeasureList: List<BodyMeasureInfo> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)