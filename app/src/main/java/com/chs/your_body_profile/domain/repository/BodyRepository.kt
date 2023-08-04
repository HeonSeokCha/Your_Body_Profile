package com.chs.your_body_profile.domain.repository

import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface BodyRepository {

    suspend fun insertInitToDayBodySummaryInfo()

    fun<reified T> getLastDayInfo(localDate: LocalDate): Flow<T?>

    fun<T> getLastDayInfoList(localDate: LocalDate): Flow<List<T>>

    suspend fun<T> upsertInfo(info: T)

    suspend fun<T> deleteInfo(info: T)
}