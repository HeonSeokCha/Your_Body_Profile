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

    suspend fun upsertTakenMealInfo(
        info: MealHistoryInfo,
        foodCodeList: List<String>
    )

    suspend fun deleteTakenMealInfo(info: List<MealHistoryInfo>)

    suspend fun getSearchResultFoodInfo(query: String): Flow<PagingData<FoodDetailInfo>>

    fun getRecentFoodSearchHistory(): Flow<List<String>>

    suspend fun getRecentTakenFoods(): List<FoodDetailInfo>

    fun getDayTotalCalories(localDate: LocalDate): Flow<Int>

    fun getDayTakenList(
        takenDate: LocalDate
    ): Flow<Map<MealHistoryInfo, List<FoodDetailInfo>>>

    fun getDayMealTypeList(
        takenDate: LocalDate,
        mealType: MealType
    ): Flow<Pair<MealHistoryInfo?, List<FoodDetailInfo>>>

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

    fun getPagingDayTotalCalories(): Flow<PagingData<Pair<LocalDate, Int>>>
}