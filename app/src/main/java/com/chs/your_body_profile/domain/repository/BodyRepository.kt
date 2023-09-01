package com.chs.your_body_profile.domain.repository

import com.chs.your_body_profile.domain.model.BloodPressureInfo
import com.chs.your_body_profile.domain.model.BloodSugarInfo
import com.chs.your_body_profile.domain.model.DrinkCoffeeInfo
import com.chs.your_body_profile.domain.model.DrinkWaterInfo
import com.chs.your_body_profile.domain.model.FoodInfo
import com.chs.your_body_profile.domain.model.HemoglobinA1cInfo
import com.chs.your_body_profile.domain.model.InsulinInfo
import com.chs.your_body_profile.domain.model.MedicineInfo
import com.chs.your_body_profile.domain.model.WeightInfo
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface BodyRepository {

    suspend fun<T> upsertInfo(info: T)

    suspend fun<T> deleteInfo(info: T)

    fun getDayLastBloodPressureInfo(localDate: LocalDate): Flow<BloodPressureInfo?>

    fun getDayBloodPressureInfoList(localDate: LocalDate): Flow<List<BloodPressureInfo>>

    fun getDayLastBloodSugarInfo(localDate: LocalDate): Flow<BloodSugarInfo?>

    fun getDayBloodSugarInfoList(localDate: LocalDate): Flow<List<BloodSugarInfo>>

    fun getDayLastDrinkWaterInfo(localDate: LocalDate): Flow<DrinkWaterInfo?>

    fun getDayLastDrinkCoffeeInfo(localDate: LocalDate): Flow<DrinkCoffeeInfo?>

    fun getDayLastHemoglobinA1cInfo(localDate: LocalDate): Flow<HemoglobinA1cInfo?>

    fun getDayHemoglobinA1cList(localDate: LocalDate): Flow<List<HemoglobinA1cInfo>>

    fun getDayLastInsulinInfo(localDate: LocalDate): Flow<InsulinInfo?>

    fun getDayInsulinList(localDate: LocalDate): Flow<List<InsulinInfo>>

    fun getDayLastMedicineInfo(localDate: LocalDate): Flow<MedicineInfo?>

    fun getDayLastFoodInfo(localDate: LocalDate): Flow<FoodInfo?>

    fun getDayTotalCalorie(localDate: LocalDate): Flow<Int>

    fun getDayLastWeightInfo(localDate: LocalDate): Flow<WeightInfo?>

}