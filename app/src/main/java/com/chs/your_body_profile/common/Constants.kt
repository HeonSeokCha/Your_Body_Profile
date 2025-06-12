package com.chs.your_body_profile.common

import androidx.annotation.FloatRange
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessibilityNew
import androidx.compose.material.icons.filled.NoFood
import androidx.compose.material.icons.filled.NoMeals
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.SetMeal
import androidx.compose.material.icons.filled.SoupKitchen
import androidx.compose.material.icons.filled.TakeoutDining
import androidx.compose.ui.graphics.vector.ImageVector
import com.chs.your_body_profile.BuildConfig
import com.chs.your_body_profile.R
import com.chs.your_body_profile.domain.model.MeasureType
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

object Constants {
    const val FOOD_BASE_URL: String = "http://openapi.foodsafetykorea.go.kr/api"
    const val FOOD_API_KEY: String = BuildConfig.API_ACCESS_KEY
    const val FOOD_PARAMETER_FOOD_NAME: String = "DESC_KOR"
    const val TYPE_JSON: String = "json"
    const val SEARCH_OFFSET: Int = 15

    val DATE_TIME_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("a hh:mm:ss")
        .withLocale(Locale.forLanguageTag("ko"))

    val DATE_TIME_FORMATTER_DETAIL: DateTimeFormatter = DateTimeFormatter.ofPattern("M월 dd일 (E) a hh:mm")
        .withLocale(Locale.forLanguageTag("ko"))

    val DATE_FORMATTER_DETAIL: DateTimeFormatter = DateTimeFormatter.ofPattern("M월 dd일 (E)")
        .withLocale(Locale.forLanguageTag("ko"))

    val DATE_MONTH_DAY_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("M/d")

    val DATE_DAY_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("d")

    val RANGE_DATE_LIST = LocalDate.now().minusYears(2L)
        .dateUntilToList(LocalDate.now())
        .reversed()

    val RANGE_TIME_MERIDIEM_LIST = listOf("오전", "오후")
    val RANGE_TIME_HOUR_LIST = 0 .. 23
    val RANGE_TIME_MIN_LIST = (0 .. 59)
    val RANGE_BLOOD_SUGAR_NUMBER = 18 .. 400
    val RANGE_BLOOD_PRESSURE_SYSTOLIC_NUMBER = 81 .. 300
    val RANGE_BLOOD_PRESSURE_DIASTOLIC_NUMBER = 0 .. 250
    val RANGE_INSULIN_NUMBER = 0 .. 100
    val RANGE_HEMOGLOBIN_A1C_FIRST_RANGE = 3 .. 15
    val RANGE_WEIGHT_FIRST_RANGE = 2 .. 500
    val RANGE_INTEGER_SECOND_RANGE = 0 .. 9
    const val ITEM_SHIMMER_SHOW_COUNT: Int = 7

    val bloodSugarMeasureList: List<Pair<String, ImageVector>> = listOf(
        MeasureType.EMPTY.mean.second to Icons.Default.NoMeals,
        MeasureType.BEFORE_EAT.mean.second to Icons.Default.SoupKitchen,
        MeasureType.AFTER_EAT.mean.second to Icons.Default.TakeoutDining,
        MeasureType.NORMAL.mean.second to Icons.Default.Person,
    )
}