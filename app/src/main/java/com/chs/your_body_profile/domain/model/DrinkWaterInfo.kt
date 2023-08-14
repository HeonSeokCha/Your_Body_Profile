package com.chs.your_body_profile.domain.model

data class DrinkWaterInfo(
    val totalCups: Int
) : DrinkType {
    override fun totalCups(): Int {
        return totalCups
    }
}
