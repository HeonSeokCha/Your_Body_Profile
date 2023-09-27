package com.chs.your_body_profile.domain.repository

import androidx.paging.PagingData
import com.chs.your_body_profile.common.Resource
import com.chs.your_body_profile.domain.model.FoodDetailInfo
import com.chs.your_body_profile.domain.model.FoodInfo
import com.chs.your_body_profile.domain.model.MealType
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface FoodRepository : BaseRepository<FoodInfo> {

    suspend fun getSearchResultFoodInfo(query: String): Flow<PagingData<FoodDetailInfo>>

    fun getRecentFoodSearchHistory(): Flow<List<String>>

    suspend fun insertSearchHistory(query: String)

    fun getDayTotalCalories(localDate: LocalDate): Flow<Int>

    fun getDayMealTypeTakenList(
        localDate: LocalDate,
        mealType: MealType
    ): Flow<List<FoodDetailInfo>>

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
}