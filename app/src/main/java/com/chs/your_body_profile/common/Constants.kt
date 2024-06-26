package com.chs.your_body_profile.common

import com.chs.your_body_profile.BuildConfig
import com.chs.your_body_profile.R
import java.time.format.DateTimeFormatter
import java.util.Locale

object Constants {
    const val FOOD_BASE_URL: String = "http://openapi.foodsafetykorea.go.kr/api"
    const val FOOD_API_KEY: String = BuildConfig.API_ACCESS_KEY
    const val FOOD_PARAMETER_FOOD_NAME: String = "DESC_KOR"
    const val TYPE_JSON: String = "json"
    const val SEARCH_OFFSET: Int = 15

    const val DRINK_TYPE_COFFEE: String = "coffee"
    const val DRINK_TYPE_WATER: String = "water"
    const val TEMP_FOOD_LIST: String = "temp_food_list"
    const val ARG_TAKEN_DATE: String = "arg_taken_date"
    const val ARG_TAKEN_MEAL_TYPE: String = "arg_taken_meal_type"

    val DATE_TIME_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("a hh:mm:ss")
        .withLocale(Locale.forLanguageTag("ko"))

    val DATE_TIME_FORMATTER_DETAIL: DateTimeFormatter = DateTimeFormatter.ofPattern("M월 dd일 (E) a hh:mm")
        .withLocale(Locale.forLanguageTag("ko"))

    val DATE_MONTH_DAY_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("M/d")

    val DATE_DAY_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("d")

    val RANGE_BLOOD_SUGAR_NUMBER = 18 .. 400
    val RANGE_BLOOD_PRESSURE_SYSTOLIC_NUMBER = 81 .. 300
    val RANGE_BLOOD_PRESSURE_DIASTOLIC_NUMBER = 0 .. 250
    val RANGE_INSULIN_NUMBER = 0 .. 100
    val RANGE_HEMOGLOBIN_A1C_FIRST_RANGE = 3 .. 15
    val RANGE_HEMOGLOBIN_A1C_SECOND_RANGE = 0 .. 9
    const val ITEM_SHIMMER_SHOW_COUNT: Int = 7

}