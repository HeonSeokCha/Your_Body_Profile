package com.chs.your_body_profile.common

import com.chs.your_body_profile.data.mapper.toFoodDetailInfo
import com.chs.your_body_profile.data.mapper.toResponseFoodDetailInfo
import com.chs.your_body_profile.data.model.dto.ResponseFoodDetailInfo
import com.chs.your_body_profile.domain.model.FoodDetailInfo
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId

fun LocalDateTime.toMillis(): Long {
    return this.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
}

fun LocalDate.toMillis(): Long {
    return this.atStartOfDay().toMillis()
}

fun LocalDate.getStartOfDayTimeMillis(): Long {
    return this.atTime(LocalTime.MIN).toMillis()
}

fun LocalDate.getEndOfDayTimeMillis(): Long {
    return this.atTime(LocalTime.MAX).toMillis()
}

fun Long.toLocalDateTime(): LocalDateTime {
    return Instant.ofEpochMilli(this).atZone(ZoneId.systemDefault()).toLocalDateTime()
}

fun Long.toLocalDate(): LocalDate {
    return Instant.ofEpochMilli(this).atZone(ZoneId.systemDefault()).toLocalDate()
}

fun Long.toLocalDateToMillis(): Long {
    return this.toLocalDate().toMillis()
}

fun List<FoodDetailInfo>.toJsonStringEncode(): String {
    return Json.encodeToString(
        this.map { foodInfo ->
            foodInfo.toResponseFoodDetailInfo()
        }
    )
}

fun String.toDecodeFoodList(): List<FoodDetailInfo> {
    return Json.decodeFromString<List<ResponseFoodDetailInfo>>(this).map {
        it.toFoodDetailInfo()
    }
}