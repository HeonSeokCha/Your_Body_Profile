package com.chs.your_body_profile.presentation.screen.body_dash_board

import com.chs.your_body_profile.domain.model.BloodPressureInfo
import com.chs.your_body_profile.domain.model.BloodSugarInfo
import com.chs.your_body_profile.domain.model.DrinkCoffeeInfo
import com.chs.your_body_profile.domain.model.DrinkWaterInfo
import com.chs.your_body_profile.domain.model.HemoglobinA1cInfo
import com.chs.your_body_profile.domain.model.InsulinInfo
import com.chs.your_body_profile.domain.model.MedicineInfo
import com.chs.your_body_profile.domain.model.WeightInfo

data class BodyDashBoardState(
    val bloodPressureInfo: BloodPressureInfo? = null,
    val bloodSugarInfo: BloodSugarInfo? = null,
    val drinkWaterInfo: DrinkWaterInfo? = null,
    val drinkCoffeeInfo: DrinkCoffeeInfo? = null,
    val hemoglobinA1cInfo: HemoglobinA1cInfo? = null,
    val insulinInfo: InsulinInfo? = null,
    val totalCalorie: Int = 0,
    val medicineInfo: MedicineInfo? = null,
    val weightInfo: WeightInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)