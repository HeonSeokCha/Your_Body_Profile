package com.chs.your_body_profile.data.repository

import com.chs.your_body_profile.common.toMillis
import com.chs.your_body_profile.data.mapper.toHemoglobinA1cInfo
import com.chs.your_body_profile.data.mapper.toHemoglobinA1cInfoEntity
import com.chs.your_body_profile.data.source.db.dao.HemoglobinA1cDao
import com.chs.your_body_profile.domain.model.HemoglobinA1cInfo
import com.chs.your_body_profile.domain.repository.BaseInfoRepository
import com.chs.your_body_profile.domain.repository.HemoglobinA1cRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

class HemoglobinA1cRepositoryImpl @Inject constructor(
    private val hemoglobinA1cDao: HemoglobinA1cDao
) : HemoglobinA1cRepository {

    override fun getDayLastInfo(localDate: LocalDate): Flow<HemoglobinA1cInfo?> {
        return hemoglobinA1cDao.getDayLastInfo(localDate.toMillis()).map {
            it?.toHemoglobinA1cInfo()
        }
    }

    override fun getDayInfoList(localDate: LocalDate): Flow<List<HemoglobinA1cInfo>> {
        return hemoglobinA1cDao.getDayInfoList(localDate.toMillis()).map {
            it.map { hemoglobinA1cInfoEntity ->
                hemoglobinA1cInfoEntity.toHemoglobinA1cInfo()
            }
        }
    }

    override suspend fun deleteInfo(info: HemoglobinA1cInfo) {
        hemoglobinA1cDao.delete(info.toHemoglobinA1cInfoEntity())
    }

    override suspend fun upsertInfo(info: HemoglobinA1cInfo) {
        hemoglobinA1cDao.upsert(info.toHemoglobinA1cInfoEntity())
    }

}