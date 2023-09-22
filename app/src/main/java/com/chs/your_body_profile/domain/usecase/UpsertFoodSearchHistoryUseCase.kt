package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.repository.FoodRepository
import javax.inject.Inject

class UpsertFoodSearchHistoryUseCase @Inject constructor(
    private val repository: FoodRepository
) {
    suspend operator fun invoke(query: String) {
        repository.insertSearchHistory(query)
    }
}