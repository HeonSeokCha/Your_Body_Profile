package com.chs.your_body_profile.domain.model

import java.time.LocalDate

sealed class DrinkType(
    open val takenDate: LocalDate = LocalDate.now(),
    open val totalCups: Int = 0
) {
    data class DrinkWaterInfo(override val takenDate: LocalDate, override val totalCups: Int): DrinkType()
    data class DrinkCoffeeInfo(override val takenDate: LocalDate, override val totalCups: Int): DrinkType()
}