package com.chs.your_body_profile.domain.model

class DrinkCoffeeInfo(
    val totalCups: Int
): DrinkType {
    override fun totalCups(): Int {
        return totalCups
    }
}