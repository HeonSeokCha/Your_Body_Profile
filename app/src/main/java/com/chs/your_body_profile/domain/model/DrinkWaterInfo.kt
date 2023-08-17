package com.chs.your_body_profile.domain.model

import java.time.LocalDate


data class DrinkWaterInfo(
    val totalCups: Int
) : DrinkType {

    override fun totalCups(): Int {
        return totalCups
    }
}
