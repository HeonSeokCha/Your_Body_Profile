package com.chs.your_body_profile.domain.model

import java.time.LocalDate

data class BodySummaryInfo(
    val insertDate: LocalDate,
    val type: String,
    val todayLastInfo: String,
    val measureUnit: String
)
