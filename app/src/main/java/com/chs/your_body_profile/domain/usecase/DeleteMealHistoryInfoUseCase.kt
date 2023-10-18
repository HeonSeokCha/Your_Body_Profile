package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.model.TakenMealInfo
import com.chs.your_body_profile.domain.repository.FoodRepository
import javax.inject.Inject

class DeleteMealHistoryInfoUseCase @Inject constructor(
    private val repository: FoodRepository
) {
    suspend operator fun invoke(info: TakenMealInfo) {
        repository.deleteTakenMealInfo(info)
    }
}