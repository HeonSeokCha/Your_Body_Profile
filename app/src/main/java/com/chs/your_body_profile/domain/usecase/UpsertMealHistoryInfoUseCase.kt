package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.repository.FoodRepository
import javax.inject.Inject

class UpsertMealHistoryInfoUseCase @Inject constructor(
    private val repository: FoodRepository
) {
    suspend operator fun invoke(
        info: TakenMealInfo,
        foodCodeList: List<String>
    ) {
        repository.upsertTakenMealInfo(
            info = info,
            foodCodeList = foodCodeList
        )
    }
}