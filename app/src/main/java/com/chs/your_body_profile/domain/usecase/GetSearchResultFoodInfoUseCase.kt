package com.chs.your_body_profile.domain.usecase

import androidx.paging.PagingData
import com.chs.your_body_profile.common.Resource
import com.chs.your_body_profile.domain.model.FoodDetailInfo
import com.chs.your_body_profile.domain.model.FoodInfo
import com.chs.your_body_profile.domain.repository.FoodRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchResultFoodInfoUseCase @Inject constructor(
    private val repository: FoodRepository
) {
    suspend operator fun invoke(query: String): Flow<PagingData<FoodDetailInfo>> {
        return repository.getSearchResultFoodInfo(query)
    }
}