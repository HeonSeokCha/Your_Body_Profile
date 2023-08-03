package com.chs.your_body_profile.domain.model

class DrinkWaterInfo(
    val totalCups: Int
) : DrinkType {
    override fun totalCups(): Int {
        return totalCups
    }
}
