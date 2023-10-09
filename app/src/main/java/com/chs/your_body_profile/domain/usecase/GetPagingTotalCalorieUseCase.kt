package com.chs.your_body_profile.domain.usecase

import androidx.paging.PagingData
import com.chs.your_body_profile.domain.repository.FoodRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPagingTotalCalorieUseCase @Inject constructor(
    private val foodRepository: FoodRepository
) {
    operator fun invoke(): Flow<PagingData<Pair<Long, Int>>> {
        return foodRepository.getPagingDayTotalCalories()
    }
}