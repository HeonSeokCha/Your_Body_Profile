package com.chs.your_body_profile.domain.repository

import androidx.paging.PagingData
import com.chs.your_body_profile.domain.model.DrinkType
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface DrinkRepository : BaseRepository<DrinkType> {

    suspend fun getDayCoffeeInfo(localDate: LocalDate): DrinkType.DrinkCoffeeInfo?

    suspend fun getDayWaterInfo(localDate: LocalDate): DrinkType.DrinkWaterInfo?

    suspend fun getDayPagingInfoList(drinkType: DrinkType): Flow<PagingData<Pair<LocalDate, List<DrinkType>>>>

}