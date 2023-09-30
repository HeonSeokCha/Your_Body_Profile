package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.model.FoodDetailInfo
import com.chs.your_body_profile.domain.model.MealHistoryInfo
import com.chs.your_body_profile.domain.repository.FoodRepository
import javax.inject.Inject

class UpsertFoodDetailInfoUseCase @Inject constructor(
    private val repository: FoodRepository
) {
    suspend operator fun invoke(
        foodDetailInfo: FoodDetailInfo,
        mealHistoryInfo: MealHistoryInfo
    ) {
        repository.upsertFoodDetailInfo(
            foodInfo = foodDetailInfo,
            mealHistoryInfo = mealHistoryInfo
        )
    }
}