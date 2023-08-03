package com.chs.your_body_profile.domain.repository

import com.chs.your_body_profile.domain.model.BloodPressureInfo
import com.chs.your_body_profile.domain.model.BloodSugarInfo
import com.chs.your_body_profile.domain.model.DrinkCoffeeInfo
import com.chs.your_body_profile.domain.model.DrinkWaterInfo
import com.chs.your_body_profile.domain.model.HemoglobinA1cInfo
import com.chs.your_body_profile.domain.model.InsulinInfo
import com.chs.your_body_profile.domain.model.MedicineInfo
import com.chs.your_body_profile.domain.model.WeightInfo
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

interface BodyRepository {

    fun getDayLastBloodPressureInfo(localDateTime: LocalDateTime): Flow<BloodPressureInfo?>

    fun getDayLastBloodSugarInfo(localDateTime: LocalDateTime): Flow<BloodSugarInfo?>

    fun getDayLastDrinkWaterInfo(localDateTime: LocalDateTime): Flow<DrinkWaterInfo?>

    suspend fun updateWaterDrinkWaterInfo(drinkWaterInfo: DrinkWaterInfo)

    fun getDayLastDrinkCoffeeInfo(localDateTime: LocalDateTime): Flow<DrinkCoffeeInfo?>

    suspend fun updateWaterDrinkCoffeeInfo(drinkCoffeeInfo: DrinkCoffeeInfo)

    fun getDayLastHemoglobinA1cInfo(localDateTime: LocalDateTime): Flow<HemoglobinA1cInfo?>

    fun getDayLastInsulinInfo(localDateTime: LocalDateTime): Flow<InsulinInfo?>

    fun getDayMedicineInfo(localDateTime: LocalDateTime): Flow<MedicineInfo?>

    fun getDayWeightInfo(localDateTime: LocalDateTime): Flow<WeightInfo?>

}