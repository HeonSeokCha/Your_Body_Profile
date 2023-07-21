package com.chs.your_body_profile.data.repository

import com.chs.your_body_profile.common.Resource
import com.chs.your_body_profile.domain.model.BodyInfo
import com.chs.your_body_profile.domain.repository.BodyRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class BodyRepositoryImpl : BodyRepository {
    override suspend fun getDayBodyInfo(date: LocalDate): Flow<Resource<BodyInfo?>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertBodyInfo(bodyInfo: BodyInfo) {
        TODO("Not yet implemented")
    }

    override suspend fun getFloatList(): List<Float> {
        TODO("Not yet implemented")
    }

    override suspend fun getIntList(): List<Int> {
        TODO("Not yet implemented")
    }
}