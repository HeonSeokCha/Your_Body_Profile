package com.chs.your_body_profile.domain.model

import java.time.LocalDate


data class InsulinInfo(
    val injectTime: LocalDate,
    val level :Int,
    val memo: String?
)