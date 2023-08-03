package com.chs.your_body_profile.domain.model

enum class MeasureType(
    val mean: Pair<String, String>
) {
    EMPTY("empty" to "공복"),
    BEFORE_EAT("beforeEat" to "식전"),
    AFTER_EAT("afterEat" to "식후"),
    NORMAL("normal" to "평상시")
}