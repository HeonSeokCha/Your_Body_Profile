package com.chs.your_body_profile.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.chs.your_body_profile.common.toMillis
import com.chs.your_body_profile.data.mapper.toFoodDetailInfo
import com.chs.your_body_profile.data.mapper.toFoodInfoEntity
import com.chs.your_body_profile.data.mapper.toMealHistoryInfo
import com.chs.your_body_profile.data.mapper.toTakenMealHistoryEntity
import com.chs.your_body_profile.data.model.entity.FoodSearchHistoryEntity
import com.chs.your_body_profile.data.source.api.FoodService
import com.chs.your_body_profile.data.source.db.dao.FoodDao
import com.chs.your_body_profile.data.source.db.dao.FoodSearchHistoryDao
import com.chs.your_body_profile.data.source.db.dao.TakenMealHistoryDao
import com.chs.your_body_profile.data.source.paging.FoodDayTotalCaloriePaging
import com.chs.your_body_profile.data.source.paging.SearchFoodPaging
import com.chs.your_body_profile.domain.model.FoodDetailInfo
import com.chs.your_body_profile.domain.model.FoodInfo
import com.chs.your_body_profile.domain.model.MealHistoryInfo
import com.chs.your_body_profile.domain.model.MealType
import com.chs.your_body_profile.domain.repository.FoodRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import java.time.LocalDate
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(
    private val foodDao: FoodDao,
    private val takenMealHistoryDao: TakenMealHistoryDao,
    private val foodSearchHistoryDao: FoodSearchHistoryDao,
    private val foodService: FoodService
) : FoodRepository {

    override suspend fun upsertFoodDetailInfo(
        foodInfoList: List<FoodDetailInfo>,
        mealHistoryInfo: MealHistoryInfo
    ) {
        foodDao.upsert(
            *foodInfoList.map {
                it.toFoodInfoEntity(mealHistoryInfo)
            }.toTypedArray()
        )
    }

    override suspend fun upsertMealHistoryInfo(info: MealHistoryInfo) {
        takenMealHistoryDao.upsert(info.toTakenMealHistoryEntity())
    }

    override suspend fun deleteMealHistoryInfo(info: MealHistoryInfo) {
        takenMealHistoryDao.delete(info.toTakenMealHistoryEntity())
    }

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
        return foodDao.getDayTotalCalories(localDate.toMillis())
    }

    override fun getDayMealTypeList(
        localDate: LocalDate,
    ): Flow<List<Pair<MealHistoryInfo, List<FoodDetailInfo>>>> {
        return takenMealHistoryDao.getDayTakenList(localDate.toMillis()).map {
            it.map { joinResultMap ->
                joinResultMap.key.toMealHistoryInfo() to joinResultMap.value.map { it.toFoodDetailInfo() }
            }
        }
    }

    override fun getDayMealTypeTotalCalories(
        localDate: LocalDate,
        mealType: MealType
    ): Flow<Int> {
        return takenMealHistoryDao.getDayMealTypeTotalCalorie(
            time = localDate.toMillis(),
            mealType = mealType.mean.first
        )
    }

    override fun getRecentFoodSearchHistory(): Flow<List<String>> {
        return foodSearchHistoryDao.getRecentSearchHistory()
    }

    override suspend fun getRecentTakenFoods(): List<FoodDetailInfo> {
        return foodDao.getRecentTakenFood().map {
            it.toFoodDetailInfo()
        }
    }

    override suspend fun insertSearchHistory(query: String) {
        foodSearchHistoryDao.upsert(
            FoodSearchHistoryEntity(query = query)
        )
    }

    override fun getMealTypeTotalCarbohydrate(
        localDate: LocalDate,
        mealType: MealType
    ): Flow<Float> {
        return takenMealHistoryDao.getDayMealTypeTotalCarbohydrate(
            time = localDate.toMillis(),
            mealType = mealType.mean.first
        )
    }

    override fun getMealTypeTotalFat(
        localDate: LocalDate,
        mealType: MealType
    ): Flow<Float> {
        return takenMealHistoryDao.getDayMealTypeTotalFat(
            time = localDate.toMillis(),
            mealType = mealType.mean.first
        )
    }

    override fun getMealTypeTotalProtein(
        localDate: LocalDate,
        mealType: MealType
    ): Flow<Float> {
        return takenMealHistoryDao.getDayMealTypeTotalProtein(
            time = localDate.toMillis(),
            mealType = mealType.mean.first
        )
    }

    override fun getPagingDayTotalCalories(): Flow<PagingData<Pair<Long, Int>>> {
        return Pager(
            PagingConfig(pageSize = 10)
        ) {
            FoodDayTotalCaloriePaging(foodDao = foodDao)
        }.flow
    }
}