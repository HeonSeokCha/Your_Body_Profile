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
import com.chs.your_body_profile.domain.model.DrinkType
import com.chs.your_body_profile.domain.repository.DrinkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

class DrinkRepositoryImpl @Inject constructor(
    private val drinkDao: DrinkDao
) : DrinkRepository {
    override suspend fun upsertInfo(info: DrinkType) {
        drinkDao.upsert(info.toDrinkInfoEntity())
    }

    override suspend fun deleteInfo(info: DrinkType) {
        drinkDao.delete(info.toDrinkInfoEntity())
    }

    override suspend fun getDayPagingInfoList(
        drinkType: DrinkType
    ): Flow<PagingData<Pair<LocalDate, List<DrinkType>>>> {
        return Pager(
            PagingConfig(pageSize = 10)
        ) {
            DayDrinkPaging(
                drinkDao = drinkDao,
                drinkType = drinkType.typeInfo
            )
        }.flow
    }

    override suspend fun getDayCoffeeInfo(localDate: LocalDate): DrinkType.DrinkCoffeeInfo? {
        return drinkDao.getDayLastDrinkInfo(
            time = localDate.toMillis(),
            drinkType = Constants.DRINK_TYPE_COFFEE
        )?.toDrinkCoffeeInfo()
    }

    override suspend fun getDayWaterInfo(localDate: LocalDate): DrinkType.DrinkWaterInfo? {
        return drinkDao.getDayLastDrinkInfo(
            time = localDate.toMillis(),
            drinkType = Constants.DRINK_TYPE_WATER
        )?.toDrinkWaterInfo()
    }
}