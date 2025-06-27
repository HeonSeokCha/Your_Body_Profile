package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.model.MealHistoryInfo
import com.chs.your_body_profile.domain.repository.BloodSugarRepository
import javax.inject.Inject

class InsertMealHistoryInoUseCase @Inject constructor(
    private val repository: BloodSugarRepository
) {
    suspend operator fun invoke(list: List<MealHistoryInfo>) {

    }
}