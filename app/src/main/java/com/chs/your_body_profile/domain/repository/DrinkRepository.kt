package com.chs.your_body_profile.domain.repository

import androidx.paging.PagingData
import com.chs.your_body_profile.domain.model.DrinkInfo
import com.chs.your_body_profile.domain.model.DrinkType
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.LocalDateTime

interface DrinkRepository : BaseRepository<DrinkInfo> {

    fun getDayDrinkInfo(drinkType: DrinkType): Flow<DrinkInfo?>

    fun getDayPagingInfoList(drinkType: DrinkType): Flow<PagingData<Pair<LocalDate, List<DrinkInfo>>>>
}