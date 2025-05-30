package com.chs.your_body_profile.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.chs.your_body_profile.common.toMillis
import com.chs.your_body_profile.data.mapper.toDrinkInfo
import com.chs.your_body_profile.data.mapper.toDrinkInfoEntity
import com.chs.your_body_profile.data.source.db.dao.DrinkDao
import com.chs.your_body_profile.data.source.paging.DayDrinkPaging
import com.chs.your_body_profile.domain.model.DrinkInfo
import com.chs.your_body_profile.domain.model.DrinkType
import com.chs.your_body_profile.domain.repository.DrinkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

class DrinkRepositoryImpl @Inject constructor(
    private val drinkDao: DrinkDao
) : DrinkRepository {
    override suspend fun upsertInfo(info: DrinkInfo) {
        drinkDao.upsert(info.toDrinkInfoEntity())
    }

    override suspend fun deleteInfo(info: DrinkInfo) {
        drinkDao.delete(info.toDrinkInfoEntity())
    }

    override fun getDayPagingInfoList(
        drinkType: DrinkType
    ): Flow<PagingData<Pair<LocalDate, List<DrinkInfo>>>> {
        return Pager(
            PagingConfig(pageSize = 10)
        ) {
            DayDrinkPaging(
                drinkDao = drinkDao,
                drinkType = drinkType
            )
        }.flow
    }

    override fun getDayDrinkInfo(
        targetDate: LocalDate,
        drinkType: DrinkType
    ): Flow<List<DrinkInfo>> {
        return drinkDao.getDayInfoList(
            targetDate = targetDate.toMillis(),
            drinkType = drinkType.name
        ).map { it.map { it.toDrinkInfo() } }
    }
}