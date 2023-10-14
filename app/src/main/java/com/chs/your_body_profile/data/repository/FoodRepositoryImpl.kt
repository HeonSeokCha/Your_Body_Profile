package com.chs.your_body_profile.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.chs.your_body_profile.common.toLocalDateTime
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
import com.chs.your_body_profile.domain.model.MealHistoryInfo
import com.chs.your_body_profile.domain.model.MealType
import com.chs.your_body_profile.domain.repository.FoodRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject
import kotlin.math.roundToInt

class FoodRepositoryImpl @Inject constructor(
    private val foodDao: FoodDao,
    private val takenMealHistoryDao: TakenMealHistoryDao,
    private val foodSearchHistoryDao: FoodSearchHistoryDao,
    private val foodService: FoodService
) : FoodRepository {

    override suspend fun upsertFoodDetailInfo(foodInfoList: List<FoodDetailInfo>) {
        foodDao.upsert(
            *foodInfoList.map {
                it.toFoodInfoEntity()
            }.toTypedArray()
        )
    }

    override suspend fun upsertMealHistoryInfo(
        info: MealHistoryInfo,
        foodCodeList: List<String>
    ) {
        takenMealHistoryDao.upsert(
            *foodCodeList.map {
                info.toTakenMealHistoryEntity(it)
            }.toTypedArray()
        )
    }

    override suspend fun deleteMealHistoryInfo(info: MealHistoryInfo) {
        takenMealHistoryDao.deleteMealHistory(
            takenDate = info.takenDate.toMillis(),
            takenTime = info.takenTime.toMillis(),
            mealType = info.mealType.mean.first
        )
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
        return takenMealHistoryDao.getDayTakenTotalCalorie(localDate.toMillis()).map {
            it.roundToInt()
        }
    }

    override fun getDayTakenList(
        takenDate: LocalDate
    ): Flow<List<Pair<MealHistoryInfo, List<FoodDetailInfo>>>> {
        return takenMealHistoryDao.getDayTakenList(takenDate.toMillis()).map {
            it.map { joinResultMap ->
                joinResultMap.key.toMealHistoryInfo() to joinResultMap.value.map {
                    it.toFoodDetailInfo()
                }
            }
        }
    }

    override fun getDayMealTypeList(
        takenDate: LocalDate,
        mealType: MealType
    ): Flow<Pair<MealHistoryInfo?, List<FoodDetailInfo>>> {
        return takenMealHistoryDao.getDayMealTypeTakenList(
            takenDate = takenDate.toMillis(),
            mealTYpe = mealType.mean.first
        ).map {
            it.keys.firstOrNull()?.toMealHistoryInfo() to it[it.keys.first()]!!.map {
                it.toFoodDetailInfo()
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
        return takenMealHistoryDao.getRecentTakenFood().map {
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

    override fun getPagingDayTotalCalories(): Flow<PagingData<Pair<LocalDate, Int>>> {
        return Pager(
            PagingConfig(pageSize = 10)
        ) {
            FoodDayTotalCaloriePaging(takenMealHistoryDao = takenMealHistoryDao)
        }.flow
    }
}