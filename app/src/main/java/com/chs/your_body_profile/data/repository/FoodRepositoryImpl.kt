package com.chs.your_body_profile.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.chs.your_body_profile.common.toLocalDateTime
import com.chs.your_body_profile.common.toMillis
import com.chs.your_body_profile.data.mapper.toFoodDetailInfo
import com.chs.your_body_profile.data.mapper.toFoodInfoEntity
import com.chs.your_body_profile.data.source.db.entity.FoodSearchHistoryEntity
import com.chs.your_body_profile.data.source.api.FoodService
import com.chs.your_body_profile.data.source.db.dao.FoodDao
import com.chs.your_body_profile.data.source.db.dao.FoodSearchHistoryDao
import com.chs.your_body_profile.data.source.db.dao.MealHistoryDao
import com.chs.your_body_profile.data.source.db.entity.MealHistoryEntity
import com.chs.your_body_profile.data.source.paging.SearchFoodPaging
import com.chs.your_body_profile.domain.model.FoodDetailInfo
import com.chs.your_body_profile.domain.model.MealType
import com.chs.your_body_profile.domain.model.MealHistoryInfo
import com.chs.your_body_profile.domain.repository.FoodRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject
import kotlin.math.roundToInt

class FoodRepositoryImpl @Inject constructor(
    private val foodDao: FoodDao,
    private val mealHistoryDao: MealHistoryDao,
    private val foodSearchHistoryDao: FoodSearchHistoryDao,
    private val foodService: FoodService
) : FoodRepository {

    override suspend fun upsertTakenMealInfo(info: MealHistoryInfo) {
        mealHistoryDao.upsert(
            *info.foodList.map {
                MealHistoryEntity(
                    insertTime = info.takenDateTime.toMillis(),
                    foodCode = it.code,
                    takenMealType = info.mealType.mean.first
                )
            }.toTypedArray()
        )

        foodDao.upsert(
            *info.foodList.map {
                it.toFoodInfoEntity()
            }.toTypedArray()
        )
    }

    override suspend fun deleteTakenMealInfo(info: List<MealHistoryInfo>) {
        info.map { mealHistoryInfo ->
            mealHistoryInfo.foodList.map {
                MealHistoryEntity(
                    insertTime = mealHistoryInfo.takenDateTime.toMillis(),
                    foodCode = it.code,
                    takenMealType = mealHistoryInfo.mealType.mean.first
                )
            }.toTypedArray()
        }
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

    override fun getDayTakenList(
        takenDate: LocalDate
    ): Flow<List<MealHistoryInfo>> {
        return mealHistoryDao.getDayMealHistoryFoodInfo(targetDate = takenDate.toMillis()).map {
            it.map {
                val mealHistoryInfo = it.key
                MealHistoryInfo(
                    takenDateTime = mealHistoryInfo.insertTime.toLocalDateTime(),
                    foodList = it.value.map { it.toFoodDetailInfo() },
                    mealType = MealType.entries.find { it.mean.first == mealHistoryInfo.takenMealType } ?: MealType.MORNING
                )
            }
        }
    }

    override fun getDayLastTakenFood(takenDate: LocalDate): Flow<FoodDetailInfo?> {
        return mealHistoryDao.getDayLastInfo(takenDate.toMillis()).map {
            it?.toFoodDetailInfo()
        }
    }

    override suspend fun getDayMealTypeList(
        takenDate: LocalDate,
        mealType: MealType
    ): MealHistoryInfo {
        return mealHistoryDao.getDayMealHistoryMealTypeFoodInfo(
            targetDate = takenDate.toMillis(),
            mealType = mealType.mean.first
        ).map {
            val mealHistoryInfo = it.key
            MealHistoryInfo(
                takenDateTime = mealHistoryInfo.insertTime.toLocalDateTime(),
                foodList = it.value.map { it.toFoodDetailInfo() },
                mealType = MealType.entries.find { it.mean.first == mealHistoryInfo.takenMealType } ?: MealType.MORNING
            )
        }.first()
    }

    override suspend fun getRecentFoodSearchHistory(): List<String> {
        return foodSearchHistoryDao.getRecentSearchHistory()
    }

    override suspend fun getRecentTakenFoods(): List<FoodDetailInfo> {
        return mealHistoryDao.getRecentTakenFoodList().map { it.toFoodDetailInfo() }
    }

    override suspend fun insertSearchHistory(query: String) {
        foodSearchHistoryDao.upsert(
            FoodSearchHistoryEntity(query)
        )
    }
}