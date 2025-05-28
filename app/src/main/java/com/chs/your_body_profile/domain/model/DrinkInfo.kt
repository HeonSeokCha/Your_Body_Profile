package com.chs.your_body_profile.domain.model

import java.time.LocalDateTime

data class DrinkInfo(
    val takenDateTime: LocalDateTime,
    val drinkType: DrinkType
)