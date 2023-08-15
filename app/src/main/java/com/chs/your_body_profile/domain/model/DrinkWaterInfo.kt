package com.chs.your_body_profile.domain.model

import java.time.LocalDate


data class DrinkWaterInfo(
    val measureTime: LocalDate,
    val totalCups: Int
) : DrinkType {

    override fun totalCups(): Int {
        return totalCups
    }

    override fun measureTime(): LocalDate {
        return measureTime
    }
}
