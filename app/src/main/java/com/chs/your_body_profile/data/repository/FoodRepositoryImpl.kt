package com.chs.your_body_profile.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.chs.your_body_profile.common.toMillis
import com.chs.your_body_profile.data.mapper.toEntity
import com.chs.your_body_profile.data.mapper.toFoodDetailInfo
import com.chs.your_body_profile.data.mapper.toFoodInfoEntity
import com.chs.your_body_profile.data.mapper.toTakenMealHistoryInfo
import com.chs.your_body_profile.data.model.entity.FoodSearchHistoryEntity
import com.chs.your_body_profile.data.model.entity.MealHistoryEntity
import com.chs.your_body_profile.data.model.entity.MealHistoryWithFood
import com.chs.your_body_profile.data.source.api.FoodService
import com.chs.your_body_profile.data.source.db.dao.FoodDao
import com.chs.your_body_profile.data.source.db.dao.FoodSearchHistoryDao
import com.chs.your_body_profile.data.source.db.dao.MealHistoryDao
import com.chs.your_body_profile.data.source.db.dao.MealHistoryWithFoodDao
import com.chs.your_body_profile.data.source.paging.FoodDayTotalCaloriePaging
import com.chs.your_body_profile.data.source.paging.SearchFoodPaging
import com.chs.your_body_profile.domain.model.FoodDetailInfo
import com.chs.your_body_profile.domain.model.MealType
import com.chs.your_body_profile.domain.model.MealHistoryInfo
import com.chs.your_body_profile.domain.repository.FoodRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject
import kotlin.math.roundToInt

class FoodRepositoryImpl @Inject constructor(
    private val foodDao: FoodDao,
    private val mealHistoryDao: MealHistoryDao,
    private val mealHistoryWithFoodDao: MealHistoryWithFoodDao,
    private val foodSearchHistoryDao: FoodSearchHistoryDao,
    private val foodService: FoodService
) : FoodRepository {

    override suspend fun upsertFoodDetailInfo(
        foodInfoList: List<FoodDetailInfo>
    ) {
        foodDao.upsert(
            *foodInfoList.map {
                it.toFoodInfoEntity()
            }.toTypedArray()
        )
    }

    override suspend fun upsertTakenMealInfo(
        info: MealHistoryInfo,
        foodCodeList: List<String>
    ) {
        mealHistoryDao.upsert(info.toEntity())

        mealHistoryWithFoodDao.upsert(
            *foodCodeList.map {
                MealHistoryWithFood(
                    takenDate = info.takenDate.toMillis(),
                    takenMealType = info.mealType.mean.first,
                    foodCode = it
                )
            }.toTypedArray()
        )
    }

    override suspend fun deleteTakenMealInfo(info: List<MealHistoryInfo>) {
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
        return mealHistoryWithFoodDao.getDayTakenTotalCalorie(localDate.toMillis()).map {
            it.roundToInt()
        }
    }

    override fun getDayTakenList(
        takenDate: LocalDate
    ): Flow<Map<MealHistoryInfo, List<FoodDetailInfo>>> {
        return mealHistoryWithFoodDao.getDayTakenList(takenDate.toMillis()).map {
            it.map { joinResult ->
                joinResult.key.toTakenMealHistoryInfo() to joinResult.value.map {
                    it.toFoodDetailInfo()
                }
            }.toMap()
        }
    }

    override fun getDayMealTypeList(
        takenDate: LocalDate,
        mealType: MealType
    ): Flow<Pair<MealHistoryInfo?, List<FoodDetailInfo>>> {
        return mealHistoryWithFoodDao.getDayMealTypeTakenList(
            takenDate = takenDate.toMillis(),
            mealTYpe = mealType.mean.first
        ).map {
            if (it.isEmpty()) {
                null to emptyList()
            } else {
                it.keys.first().toTakenMealHistoryInfo() to it[it.keys.first()]!!.map {
                    it.toFoodDetailInfo()
                }
            }
        }
    }

    override fun getDayMealTypeTotalCalories(
        localDate: LocalDate,
        mealType: MealType
    ): Flow<Int> {
        return mealHistoryWithFoodDao.getDayMealTypeTotalCalorie(
            time = localDate.toMillis(),
            mealType = mealType.mean.first
        )
    }

    override fun getRecentFoodSearchHistory(): Flow<List<String>> {
        return foodSearchHistoryDao.getRecentSearchHistory()
    }

    override suspend fun getRecentTakenFoods(): List<FoodDetailInfo> {
        return mealHistoryWithFoodDao.getRecentTakenFood().map {
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
        return mealHistoryWithFoodDao.getDayMealTypeTotalCarbohydrate(
            time = localDate.toMillis(),
            mealType = mealType.mean.first
        )
    }

    override fun getMealTypeTotalFat(
        localDate: LocalDate,
        mealType: MealType
    ): Flow<Float> {
        return mealHistoryWithFoodDao.getDayMealTypeTotalFat(
            time = localDate.toMillis(),
            mealType = mealType.mean.first
        )
    }

    override fun getMealTypeTotalProtein(
        localDate: LocalDate,
        mealType: MealType
    ): Flow<Float> {
        return mealHistoryWithFoodDao.getDayMealTypeTotalProtein(
            time = localDate.toMillis(),
            mealType = mealType.mean.first
        )
    }

    override fun getPagingDayTotalCalories(): Flow<PagingData<Pair<LocalDate, Int>>> {
        return Pager(
            PagingConfig(pageSize = 10)
        ) {
            FoodDayTotalCaloriePaging(mealHistoryWithFoodDao = mealHistoryWithFoodDao)
        }.flow
    }
}