package com.chs.your_body_profile.common

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.util.stream.Collectors

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

fun LocalDate.dateUntilToList(targetDate: LocalDate): List<LocalDate> {
    return this.datesUntil(targetDate.plusDays(1L))
        .collect(Collectors.toList())
}

fun calculateScale(viewHeightPx: Int, values: List<Int>): Double {
    return values.maxOrNull()?.let { max ->
        viewHeightPx.times(0.8).div(max)
    } ?: 1.0
}