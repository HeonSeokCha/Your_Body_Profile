package com.chs.your_body_profile.domain.repository

import com.chs.your_body_profile.common.Resource
import com.chs.your_body_profile.domain.model.BodyInfo
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface BodyRepository {

    suspend fun getDayBodyInfo(date: LocalDate): Flow<Resource<BodyInfo?>>

    suspend fun insertBodyInfo(bodyInfo: BodyInfo)

    suspend fun getFloatList(): List<Float>

    suspend fun getIntList(): List<Int>
}