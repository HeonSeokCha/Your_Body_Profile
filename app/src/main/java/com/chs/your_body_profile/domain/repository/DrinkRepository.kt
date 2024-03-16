package com.chs.your_body_profile.domain.repository

import androidx.paging.PagingData
import com.chs.your_body_profile.domain.model.DrinkCoffeeInfo
import com.chs.your_body_profile.domain.model.DrinkInfo
import com.chs.your_body_profile.domain.model.DrinkWaterInfo
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface DrinkRepository : BaseRepository<DrinkInfo> {

    suspend fun getDayCoffeeInfo(localDate: LocalDate): DrinkCoffeeInfo?

    suspend fun getDayWaterInfo(localDate: LocalDate): DrinkWaterInfo?

    fun getDayPagingInfoList(drinkType: String): Flow<PagingData<Pair<LocalDate, List<Int>>>>

}