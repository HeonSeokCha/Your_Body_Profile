package com.chs.your_body_profile.presentation

import com.chs.your_body_profile.domain.model.DrinkType
import kotlinx.serialization.Serializable

@Serializable
sealed class Screens {
    @Serializable
    data object Home : Screens()

    @Serializable
    data class BloodSugarInput(val info: Int? = null) : Screens()

    @Serializable
    data class BloodPressureInput(
        val diastolic: Int? = null,
        val systolic: Int? = null
    ) : Screens()

    @Serializable
    data class HemoglobinA1cInput(val info: Float? = null) : Screens()

    @Serializable
    data class InsulinInput(val info: Int? = null) : Screens()

    @Serializable
    data class WeightInput(val info: Float? = null) : Screens()

    @Serializable
    data object MedicineInput : Screens()

    @Serializable
    data object FoodSearch : Screens()

    @Serializable
    data object FoodDetail : Screens()

    @Serializable
    data object MealHistoryInput : Screens()

    @Serializable
    data class DrinkList(val drinkType: DrinkType) : Screens()

    @Serializable
    data object BloodSugarList : Screens()
    @Serializable
    data object BloodPressureList : Screens()
    @Serializable
    data object HemoglobinA1cList : Screens()
    @Serializable
    data object InsulinList : Screens()
    @Serializable
    data object WeightList : Screens()
    @Serializable
    data object MealList : Screens()
}
