package com.chs.your_body_profile.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface BaseRepository <T> {

    suspend fun upsertInfo(info: T)

    suspend fun deleteInfo(info: T)


}