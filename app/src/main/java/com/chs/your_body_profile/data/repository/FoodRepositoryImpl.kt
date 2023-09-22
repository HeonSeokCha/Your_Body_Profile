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

    override fun getDayLastInfo(localDate: LocalDate): Flow<FoodInfo?> {
        return foodDao.getDayLastFoodInfo(localDate.toMillis()).map {
            it?.toFoodInfo()
        }
    }

    override fun getDayInfoList(localDate: LocalDate): Flow<List<FoodInfo>> {
        return foodDao.getDayLastFoodInfoList(localDate.toMillis()).map {
            it.map { foodInfoEntity ->
                foodInfoEntity.toFoodInfo()
            }
        }
    }

    override suspend fun upsertInfo(info: FoodInfo) {
        foodDao.upsert(info.toFoodInfoEntity())
    }

    override suspend fun deleteInfo(info: FoodInfo) {
        foodDao.delete(info.toFoodInfoEntity())
    }

    override fun getRecentFoodSearchHistory(): Flow<List<String>> {
        return foodSearchHistoryDao.getRecentSearchHistory()
    }

    override suspend fun insertSearchHistory(query: String) {
        foodSearchHistoryDao.upsert(
            FoodSearchHistoryEntity(query = query)
        )
    }
}