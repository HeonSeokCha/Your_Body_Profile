package com.chs.your_body_profile.domain.repository

import androidx.paging.PagingData
import com.chs.your_body_profile.common.Resource
import com.chs.your_body_profile.domain.model.FoodDetailInfo
import com.chs.your_body_profile.domain.model.FoodInfo
import kotlinx.coroutines.flow.Flow

interface FoodRepository {
    suspend fun getSearchResultFoodInfo(query: String): Flow<PagingData<FoodDetailInfo>>
}