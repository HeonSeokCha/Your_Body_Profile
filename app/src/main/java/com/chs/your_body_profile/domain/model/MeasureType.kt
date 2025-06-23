package com.chs.your_body_profile.domain.model

enum class MeasureType(
    val mean: Pair<Int, String>
) {
    EMPTY(0 to "공복"),
    BEFORE_EAT(1 to "식전"),
    AFTER_EAT(2 to "식후 2시간"),
    NORMAL(3 to "평상시")
}