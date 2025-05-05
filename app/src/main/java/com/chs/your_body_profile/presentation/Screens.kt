package com.chs.your_body_profile.presentation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screens {
    @Serializable
    data object Home : Screens()

    @Serializable
    data class DrinkList(
        val drinkType: String,
        val targetDateMilli: Long
    ) : Screens()

    @Serializable
    data class BloodSugarInput(
        val targetDateMilli: Long
    ) : Screens()

    @Serializable
    data class BloodSugarList(
        val targetDateMilli: Long
    ) : Screens()

    @Serializable
    data class BloodPressureInput(
        val targetDateMilli: Long
    ) : Screens()

    @Serializable
    data class BloodPressureList(
        val targetDateMilli: Long
    ) : Screens()

    @Serializable
    data class HemoglobinA1cInput(
        val targetDateMilli: Long
    ) : Screens()

    @Serializable
    data class MedicineInput(
        val targetDateMilli: Long
    ) : Screens()

    @Serializable
    data class FoodSearch(
        val targetDateMilli: Long
    ) : Screens()

    @Serializable
    data class FoodDetail(
        val targetDateMilli: Long
    ) : Screens()

    @Serializable
    data class MealHistoryInput(
        val targetDateMilli: Long
    ) : Screens()

    @Serializable
    data class MealList(
        val targetDateMilli: Long
    ) : Screens()
}
