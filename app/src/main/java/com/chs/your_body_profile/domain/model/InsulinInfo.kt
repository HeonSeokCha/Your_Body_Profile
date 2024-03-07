package com.chs.your_body_profile.domain.model

import java.time.LocalDateTime

data class InsulinInfo(
    val injectDateTime: LocalDateTime,
    val level :Int,
    val memo: String?
)