package com.chs.your_body_profile.domain.model

enum class MedicineType(
    val time: Pair<Int, String>
) {
    BREAKFAST(0 to "아침"),
    LUNCH(1 to "점심"),
    DINNER(2 to "저녁"),
    UNKNOWN(3 to "미 복용")
}