package com.chs.your_body_profile.domain.model

enum class MeasureType(
    val mean: Pair<Int, String>
) {
    BEFORE_EAT(0 to "식전"),
    AFTER_EAT(1 to "식후 2시간")
}