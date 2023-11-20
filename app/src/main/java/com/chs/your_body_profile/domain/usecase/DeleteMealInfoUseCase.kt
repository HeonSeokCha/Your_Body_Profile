package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.model.MealHistoryInfo
import com.chs.your_body_profile.domain.repository.FoodRepository
import javax.inject.Inject

class DeleteMealInfoUseCase @Inject constructor(
    private val repository: FoodRepository
) {
    suspend operator fun invoke(info: List<MealHistoryInfo>) {
        repository.deleteTakenMealInfo(info)
    }
}
