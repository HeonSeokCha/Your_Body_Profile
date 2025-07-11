package com.chs.your_body_profile.domain.model

enum class MealType(
    val mean: Pair<Int, String>
) {
    MORNING(0 to "아침"),
    EVENING_SNACK(1 to "오전 간식"),
    LAUNCH(2 to "점심"),
    AFTERNOON_SNACK(3 to "오후 간식"),
    DINNER(4 to "저녁"),
    NIGHT_SNACK(5 to "저녁 간식")
}