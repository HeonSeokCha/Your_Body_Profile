package com.chs.your_body_profile.domain.repository

import com.chs.your_body_profile.domain.model.BodyMeasureInfo
import kotlinx.coroutines.flow.Flow

interface BodyRepository {

    fun getBodyMeasureList(): Flow<List<BodyMeasureInfo>>

    suspend fun updateBodyMeasureInfo(bodyMeasureInfo: BodyMeasureInfo)

}