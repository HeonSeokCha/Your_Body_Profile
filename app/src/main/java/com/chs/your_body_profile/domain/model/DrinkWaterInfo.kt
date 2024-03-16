package com.chs.your_body_profile.domain.model

import java.time.LocalDateTime

data class DrinkWaterInfo(
    override val takenDateTime: LocalDateTime,
    override val totalCups: Int
) : DrinkInfo
