package com.chs.your_body_profile.presentation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screens {
    @Serializable
    data object Home : Screens()

    @Serializable
    data class DrinkList(val drinkType: String) : Screens()

    @Serializable
    data object BloodSugarInput : Screens()

    @Serializable
    data object BloodSugarList : Screens()

    @Serializable
    data object BloodPressureInput : Screens()

    @Serializable
    data object BloodPressureList : Screens()

    @Serializable
    data object HemoglobinA1cInput : Screens()

    @Serializable
    data object HemoglobinA1cList : Screens()

    @Serializable
    data object InsulinInput : Screens()

    @Serializable
    data object InsulinList : Screens()

    @Serializable
    data object WeightInput : Screens()

    @Serializable
    data object WeightList : Screens()

    @Serializable
    data object MedicineInput : Screens()

    @Serializable
    data object FoodSearch : Screens()

    @Serializable
    data object FoodDetail : Screens()

    @Serializable
    data object MealHistoryInput : Screens()

    @Serializable
    data object MealList : Screens()
}
