package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.repository.FoodRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecentFoodSearchHistoryUseCase @Inject constructor(
    private val repository: FoodRepository
) {
    operator fun invoke(): Flow<List<String>> {
        return repository.getRecentFoodSearchHistory()
    }
}