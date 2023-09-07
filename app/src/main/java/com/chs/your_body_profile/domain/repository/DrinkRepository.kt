package com.chs.your_body_profile.domain.repository

import com.chs.your_body_profile.domain.model.DrinkCoffeeInfo
import com.chs.your_body_profile.domain.model.DrinkType
import com.chs.your_body_profile.domain.model.DrinkWaterInfo
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface DrinkRepository : BaseRepository<DrinkType> {


    fun getDayCoffeeInfo(localDate: LocalDate): Flow<DrinkCoffeeInfo?>

    fun getDayWaterInfo(localDate: LocalDate): Flow<DrinkWaterInfo?>

}