package com.chs.your_body_profile.domain.model

import java.time.LocalDateTime

data class DrinkCoffeeInfo(
    override val takenDateTime: LocalDateTime,
    override val totalCups: Int
) : DrinkInfo
