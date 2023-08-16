package com.chs.your_body_profile.domain.model

enum class MealType(
    val mean: Pair<String, String>
) {
    MORNING("morning" to "아침"),
    LAUNCH("launch" to "점심"),
    DINNER("dinner" to "저녁"),
    EVENING_SNACK("evening_snack" to "오전 간식"),
    AFTERNOON_SNACK("afternoon_snack" to "오후 간식"),
    NIGHT_SNACK("night_snack" to "저녁 간식"),
    UNKNOWN("unknown" to "없음")
}