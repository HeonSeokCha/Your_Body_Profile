package com.chs.your_body_profile.domain.repository

import androidx.paging.PagingData
import com.chs.your_body_profile.domain.model.FoodDetailInfo
import com.chs.your_body_profile.domain.model.MealType
import com.chs.your_body_profile.domain.model.MealHistoryInfo
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface FoodRepository {

    suspend fun insertSearchHistory(query: String)

    suspend fun upsertFoodDetailInfo(foodInfoList: List<FoodDetailInfo>)

    suspend fun upsertTakenMealInfo(info: MealHistoryInfo)

    suspend fun deleteTakenMealInfo(info: List<MealHistoryInfo>)

    suspend fun getSearchResultFoodInfo(query: String): Flow<PagingData<FoodDetailInfo>>

    suspend fun getRecentFoodSearchHistory(): List<String>

    suspend fun getRecentTakenFoods(): List<FoodDetailInfo>

    suspend fun getDayTotalCalories(localDate: LocalDate): Int

    suspend fun getDayTakenList(
        takenDate: LocalDate
    ): List<MealHistoryInfo>

    suspend fun getDayMealTypeList(
        takenDate: LocalDate,
        mealType: MealType
    ): MealHistoryInfo

    fun getPagingDayTotalCalories(): Flow<PagingData<Pair<LocalDate, List<MealHistoryInfo>>>>
}