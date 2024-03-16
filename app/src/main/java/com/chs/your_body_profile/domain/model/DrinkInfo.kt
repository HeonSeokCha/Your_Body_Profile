package com.chs.your_body_profile.domain.model

import java.time.LocalDateTime

interface DrinkInfo {
    val takenDateTime: LocalDateTime
    val totalCups: Int
}