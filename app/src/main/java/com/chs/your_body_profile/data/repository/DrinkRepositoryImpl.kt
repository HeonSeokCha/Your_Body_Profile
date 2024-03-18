package com.chs.your_body_profile.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.common.toMillis
import com.chs.your_body_profile.data.mapper.toDrinkCoffeeInfo
import com.chs.your_body_profile.data.mapper.toDrinkInfoEntity
import com.chs.your_body_profile.data.mapper.toDrinkWaterInfo
import com.chs.your_body_profile.data.source.db.dao.DrinkDao
import com.chs.your_body_profile.data.source.paging.DayDrinkPaging
import com.chs.your_body_profile.domain.model.DrinkCoffeeInfo
import com.chs.your_body_profile.domain.model.DrinkInfo
import com.chs.your_body_profile.domain.model.DrinkWaterInfo
import com.chs.your_body_profile.domain.repository.DrinkRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class DrinkRepositoryImpl @Inject constructor(
    private val drinkDao: DrinkDao
) : DrinkRepository {
    override suspend fun upsertInfo(info: DrinkInfo) {
        when (info) {
            is DrinkWaterInfo -> {
                drinkDao.upsert(info.toDrinkInfoEntity(Constants.DRINK_TYPE_WATER))
            }

            is DrinkCoffeeInfo -> {
                drinkDao.upsert(info.toDrinkInfoEntity(Constants.DRINK_TYPE_COFFEE))
            }
        }
    }

    override suspend fun deleteInfo(info: DrinkInfo) {
        when (info) {
            is DrinkWaterInfo -> {
                drinkDao.upsert(info.toDrinkInfoEntity(Constants.DRINK_TYPE_WATER))
            }

            is DrinkCoffeeInfo -> {
                drinkDao.upsert(info.toDrinkInfoEntity(Constants.DRINK_TYPE_COFFEE))
            }
        }
    }

    override fun getDayPagingInfoList(
        drinkType: String
    ): Flow<PagingData<Pair<LocalDate, Int>>> {
        return Pager(
            PagingConfig(pageSize = 10)
        ) {
            DayDrinkPaging(
                drinkDao = drinkDao,
                drinkType = drinkType
            )
        }.flow
    }

    override suspend fun getDayCoffeeInfo(localDate: LocalDate): DrinkCoffeeInfo? {
        return drinkDao.getDayLastDrinkInfo(
            time = localDate.toMillis(),
            drinkType = Constants.DRINK_TYPE_COFFEE
        )?.toDrinkCoffeeInfo()
    }

    override suspend fun getDayWaterInfo(localDate: LocalDate): DrinkWaterInfo? {
        return drinkDao.getDayLastDrinkInfo(
            time = localDate.toMillis(),
            drinkType = Constants.DRINK_TYPE_WATER
        )?.toDrinkWaterInfo()
    }
}