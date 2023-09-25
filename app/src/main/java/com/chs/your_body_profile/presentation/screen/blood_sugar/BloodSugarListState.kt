package com.chs.your_body_profile.presentation.screen.blood_sugar

import com.chs.your_body_profile.domain.model.BloodSugarInfo

data class BloodSugarListState(
    val dailyBloodSugarInfoList: List<BloodSugarInfo> = emptyList(),
    val dailyInfoList: List<Triple<String, Int, Int>> = emptyList(),
)
