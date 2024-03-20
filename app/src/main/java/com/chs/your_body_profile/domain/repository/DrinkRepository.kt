package com.chs.your_body_profile.domain.repository

import androidx.paging.PagingData
import com.chs.your_body_profile.domain.model.DrinkCoffeeInfo
import com.chs.your_body_profile.domain.model.DrinkInfo
import com.chs.your_body_profile.domain.model.DrinkWaterInfo
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.LocalDateTime

interface DrinkRepository : BaseRepository<DrinkInfo> {

    fun getDayCoffeeInfo(time: LocalDateTime): Flow<DrinkCoffeeInfo?>

    fun getDayWaterInfo(time: LocalDateTime): Flow<DrinkWaterInfo?>

    fun getDayPagingInfoList(drinkType: String): Flow<PagingData<Pair<LocalDate, Int>>>

}