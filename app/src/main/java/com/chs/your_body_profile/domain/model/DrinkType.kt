package com.chs.your_body_profile.domain.model

sealed interface DrinkType {
    class DrinkCoffeeInfo
    class DrinkWaterInfo
    fun totalCups(): Int
}