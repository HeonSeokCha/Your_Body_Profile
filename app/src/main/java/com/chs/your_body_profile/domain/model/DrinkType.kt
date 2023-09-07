package com.chs.your_body_profile.domain.model

sealed interface DrinkType {
    fun totalCups(): Int
}