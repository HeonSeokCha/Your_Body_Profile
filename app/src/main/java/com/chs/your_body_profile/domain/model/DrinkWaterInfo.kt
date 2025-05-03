package com.chs.your_body_profile.domain.model

import java.time.LocalDate

data class DrinkWaterInfo(
    override val takenDateTime: LocalDate,
    override val totalCups: Int
) : DrinkInfo
