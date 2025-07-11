package com.chs.your_body_profile.presentation.screen.home

import com.chs.your_body_profile.domain.model.BloodPressureInfo
import com.chs.your_body_profile.domain.model.BloodSugarInfo
import com.chs.your_body_profile.domain.model.DrinkInfo
import com.chs.your_body_profile.domain.model.FoodDetailInfo
import com.chs.your_body_profile.domain.model.HemoglobinA1cInfo
import com.chs.your_body_profile.domain.model.InsulinInfo
import com.chs.your_body_profile.domain.model.PaymentInfo
import com.chs.your_body_profile.domain.model.WeightInfo

data class HomeState(
    val bloodPressureInfo: BloodPressureInfo? = null,
    val bloodSugarInfo: BloodSugarInfo? = null,
    val drinkWaterTotalCupInfo: List<DrinkInfo> = emptyList(),
    val drinkCoffeeTotalCupInfo: List<DrinkInfo> = emptyList(),
    val insulinInfo: InsulinInfo? = null,
    val hemoglobinA1cInfo: HemoglobinA1cInfo? = null,
    val takenFoodInfo: FoodDetailInfo? = null,
    val weightInfo: WeightInfo? = null,
    val payInfo: PaymentInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)