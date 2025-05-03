package com.chs.your_body_profile.domain.model

import java.time.LocalDate

data class DrinkCoffeeInfo(
    override val takenDateTime: LocalDate,
    override val totalCups: Int
) : DrinkInfo
