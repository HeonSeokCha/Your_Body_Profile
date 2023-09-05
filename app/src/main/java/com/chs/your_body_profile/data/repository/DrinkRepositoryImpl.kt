package com.chs.your_body_profile.data.repository

import com.chs.your_body_profile.data.mapper.toDrinkInfoEntity
import com.chs.your_body_profile.data.source.db.dao.DrinkDao
import com.chs.your_body_profile.domain.model.DrinkCoffeeInfo
import com.chs.your_body_profile.domain.model.DrinkType
import com.chs.your_body_profile.domain.model.DrinkWaterInfo
import com.chs.your_body_profile.domain.repository.DrinkRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class DrinkRepositoryImpl @Inject constructor(
    private val drinkDao: DrinkDao
) : DrinkRepository {
    override suspend fun upsertInfo(info: DrinkType) {
        drinkDao.upsert(info.toDrinkInfoEntity(info))
    }

    override suspend fun deleteInfo(info: DrinkType) {
        drinkDao.delete(info.toDrinkInfoEntity(info))
    }

    override fun getDayLastInfo(localDate: LocalDate): Flow<DrinkType?> {
    }

    override fun getDayInfoList(localDate: LocalDate): Flow<List<DrinkType>> {
        TODO("Not yet implemented")
    }
}