package com.chs.your_body_profile.domain.repository

import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface BodyRepository {

    suspend fun<T> upsertInfo(info: T)

    suspend fun<T> deleteInfo(info: T)

    suspend fun insertInitToDayBodySummaryInfo()

}