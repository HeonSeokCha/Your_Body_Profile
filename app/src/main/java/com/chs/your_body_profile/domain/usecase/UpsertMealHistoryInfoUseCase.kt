package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.model.MealHistoryInfo
import com.chs.your_body_profile.domain.repository.FoodRepository
import javax.inject.Inject

class UpsertMealHistoryInfoUseCase @Inject constructor(
    private val repository: FoodRepository
) {
    suspend operator fun invoke(
        info: MealHistoryInfo,
        foodCodeList: List<String>
    ) {
        repository.upsertMealHistoryInfo(
            info = info,
            foodCodeList = foodCodeList
        )
    }
}