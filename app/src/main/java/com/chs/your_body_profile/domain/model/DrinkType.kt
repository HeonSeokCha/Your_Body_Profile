package com.chs.your_body_profile.domain.model

import java.time.LocalDate

sealed interface DrinkType {
    class DrinkCoffeeInfo
    class DrinkWaterInfo
    fun totalCups(): Int
}