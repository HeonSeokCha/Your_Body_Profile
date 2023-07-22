package com.chs.your_body_profile.data.mapper

import com.chs.your_body_profile.data.model.ResponseFoodInfo
import com.chs.your_body_profile.domain.model.FoodInfo

fun ResponseFoodInfo.toFoodInfo(): FoodInfo() {
    return FoodInfo(

    )
}