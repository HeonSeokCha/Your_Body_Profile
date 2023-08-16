package com.chs.your_body_profile.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.chs.your_body_profile.data.source.api.FoodService
import com.chs.your_body_profile.data.source.paging.SearchFoodPaging
import com.chs.your_body_profile.domain.model.FoodDetailInfo
import com.chs.your_body_profile.domain.model.FoodInfo
import com.chs.your_body_profile.domain.repository.FoodRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(
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
}