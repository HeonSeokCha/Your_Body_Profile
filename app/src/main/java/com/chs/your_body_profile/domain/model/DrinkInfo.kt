package com.chs.your_body_profile.domain.model

import java.time.LocalDate

interface DrinkInfo {
    val takenDateTime: LocalDate
    val totalCups: Int
}