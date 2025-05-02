package com.chs.your_body_profile.presentation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screens {
    @Serializable
    data object ScreenBodyDash : Screens()

    @Serializable
    data class ScreenBloodSugarInput(
        val targetDateMilli: Long
    ) : Screens()

    @Serializable
    data class ScreenBloodSugarList(
        val targetDateMilli: Long
    ) : Screens()

    @Serializable
    data class ScreenBloodPressureInput(
        val targetDateMilli: Long
    ) : Screens()

    @Serializable
    data class ScreenBloodPressureList(
        val targetDateMilli: Long
    ) : Screens()

    @Serializable
    data class ScreenInsulinInput(
        val targetDateMilli: Long
    ) : Screens()

    @Serializable
    data class ScreenInsulinList(
        val targetDateMilli: Long
    ) : Screens()

    @Serializable
    data class ScreenHemoglobinA1cInput(
        val targetDateMilli: Long
    ) : Screens()

    @Serializable
    data class ScreenMedicineInput(
        val targetDateMilli: Long
    ) : Screens()

    @Serializable
    data class ScreenFoodSearch(
        val targetDateMilli: Long
    ) : Screens()

    @Serializable
    data class ScreenFoodDetail(
        val targetDateMilli: Long
    ) : Screens()

    @Serializable
    data class ScreenMealHistoryInput(
        val targetDateMilli: Long
    ) : Screens()

    @Serializable
    data class ScreenMealList(
        val targetDateMilli: Long
    ) : Screens()
}
