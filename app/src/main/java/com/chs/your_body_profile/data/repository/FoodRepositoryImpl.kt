package com.chs.your_body_profile.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.chs.your_body_profile.common.toMillis
import com.chs.your_body_profile.data.mapper.toFoodInfo
import com.chs.your_body_profile.data.mapper.toFoodInfoEntity
import com.chs.your_body_profile.data.model.entity.FoodSearchHistoryEntity
import com.chs.your_body_profile.data.source.api.FoodService
import com.chs.your_body_profile.data.source.db.dao.FoodDao
import com.chs.your_body_profile.data.source.db.dao.FoodSearchHistoryDao
import com.chs.your_body_profile.data.source.paging.SearchFoodPaging
import com.chs.your_body_profile.domain.model.FoodDetailInfo
import com.chs.your_body_profile.domain.model.FoodInfo
import com.chs.your_body_profile.domain.model.MealType
import com.chs.your_body_profile.domain.repository.FoodRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(
    private val foodDao: FoodDao,
    private val foodSearchHistoryDao: FoodSearchHistoryDao,
    private val foodService: FoodService
) : FoodRepository {
    override suspend fun getSearchResultFoodInfo(query: String): Flow<PagingData<FoodDetailInfo>> {
        return Pager(
            PagingConfig(pageSize = 10)
        ) {
            SearchFoodPaging(
                service = foodService,
                query = query
            )
        }.flow
    }

    override fun getDayTotalCalories(localDate: LocalDate): Flow<Int> {
        return foodDao.getDayTotalCalorie(localDate.toMillis())
    }

    override fun getDayMealTypeTakenList(
        localDate: LocalDate,
        mealType: MealType
    ): Flow<List<FoodDetailInfo>> {
    }


    override suspend fun upsertInfo(info: FoodInfo) {
    }

    override suspend fun deleteInfo(info: FoodInfo) {
    }

    override fun getRecentFoodSearchHistory(): Flow<List<String>> {
        return foodSearchHistoryDao.getRecentSearchHistory()
    }

    override suspend fun insertSearchHistory(query: String) {
        foodSearchHistoryDao.upsert(
            FoodSearchHistoryEntity(query = query)
        )
    }

    override fun getMealTypeTotalCalories(
        localDate: LocalDate,
        mealType: MealType
    ): Flow<Int> {
        return foodDao.getDayMealTypeTotalCalories(
            localDate.toMillis(),
            mealType.mean.first
        )
    }

    override fun getMealTypeTotalCarbohydrate(
        localDate: LocalDate,
        mealType: MealType
    ): Flow<Double> {
        return foodDao.getDayMealTypeTotalCarbohydrate(
            localDate.toMillis(),
            mealType.mean.first
        )
    }

    override fun getMealTypeTotalFat(localDate: LocalDate, mealType: MealType): Flow<Double> {
        return foodDao.getDayMealTypeTotalFat(
            localDate.toMillis(),
            mealType.mean.first
        )
    }

    override fun getMealTypeTotalProtein(localDate: LocalDate, mealType: MealType): Flow<Double> {
        return foodDao.getDayMealTypeTotalProtein(
            localDate.toMillis(),
            mealType.mean.first
        )
    }
}