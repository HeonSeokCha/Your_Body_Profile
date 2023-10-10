package com.chs.your_body_profile.domain.repository

import androidx.paging.PagingData
import com.chs.your_body_profile.domain.model.FoodDetailInfo
import com.chs.your_body_profile.domain.model.MealHistoryInfo
import com.chs.your_body_profile.domain.model.MealType
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface FoodRepository {
    suspend fun insertSearchHistory(query: String)

    suspend fun upsertFoodDetailInfo(
        foodInfoList: List<FoodDetailInfo>,
        mealHistoryInfo: MealHistoryInfo
    )

    suspend fun upsertMealHistoryInfo(info: MealHistoryInfo)

    suspend fun deleteMealHistoryInfo(info: MealHistoryInfo)

    suspend fun getSearchResultFoodInfo(query: String): Flow<PagingData<FoodDetailInfo>>

    fun getRecentFoodSearchHistory(): Flow<List<String>>

    suspend fun getRecentTakenFoods(): List<FoodDetailInfo>

    fun getDayTotalCalories(localDate: LocalDate): Flow<Int>

    fun getDayMealTypeList(localDate: LocalDate): Flow<List<Pair<MealHistoryInfo, List<FoodDetailInfo>>>>

    fun getDayMealTypeTotalCalories(
        localDate: LocalDate,
        mealType: MealType
    ): Flow<Int>

    fun getMealTypeTotalCarbohydrate(
        localDate: LocalDate,
        mealType: MealType
    ): Flow<Float>

    fun getMealTypeTotalFat(
        localDate: LocalDate,
        mealType: MealType
    ): Flow<Float>

    fun getMealTypeTotalProtein(
        localDate: LocalDate,
        mealType: MealType
    ): Flow<Float>

    fun getPagingDayTotalCalories(): Flow<PagingData<Pair<Long, Int>>>
}