package com.chs.your_body_profile.common

import com.chs.your_body_profile.R
import java.time.format.DateTimeFormatter
import java.util.Locale

object Constants {
    const val FOOD_BASE_URL: String = "http://apis.data.go.kr/1470000/FoodNtrIrdntInfoService/getFoodNtrItdntList"
    const val FOOD_PARAMETER_SERVICE_KEY: String = "ServiceKey"
    const val FOOD_PARAMETER_FOOD_NAME: String = "desc_kor"
    const val FOOD_PARAMETER_PAGE_NO: String = "pageNo"
    const val FOOD_PARAMETER_RESULT_NO: String = "numOfRows"
    const val FOOD_PARAMETER_type: String = "type"
    const val TYPE_JSON: String = "json"

    const val DRINK_TYPE_COFFEE: String = "coffee"
    const val DRINK_TYPE_WATER: String = "water"

    const val BODY_SUMMARY_TYPE_BLOOD_PRESSURE: String = "blood_pressure"
    const val BODY_SUMMARY_TYPE_BLOOD_SUGAR: String = "blood_sugar"
    const val BODY_SUMMARY_TYPE_WATER: String = "water"
    const val BODY_SUMMARY_TYPE_COFFEE: String = "coffee"
    const val BODY_SUMMARY_TYPE_FOOD: String = "food"
    const val BODY_SUMMARY_TYPE_HEMOGLOBIN_A1C: String = "hemoglobin_a1c"
    const val BODY_SUMMARY_TYPE_INSULIN: String = "insulin"
    const val BODY_SUMMARY_TYPE_MEDICINE: String = "medicine"
    const val BODY_SUMMARY_TYPE_WEIGHT: String = "weight"

    val DATE_TIME_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("a hh:mm:ss")
        .withLocale(Locale.forLanguageTag("ko"))

    val DATE_TIME_FORMATTER_DETAIL: DateTimeFormatter = DateTimeFormatter.ofPattern("M월 dd일 (E) a hh:mm")
        .withLocale(Locale.forLanguageTag("ko"))

    val RANGE_BLOOD_SUGAR_NUMBER = 18 .. 400
    val RANGE_BLOOD_PRESSURE_SYSTOLIC_NUMBER = 81 .. 300
    val RANGE_BLOOD_PRESSURE_DIASTOLIC_NUMBER = 0 .. 250
    val RANGE_INSULIN_NUMBER = 0 .. 100
}