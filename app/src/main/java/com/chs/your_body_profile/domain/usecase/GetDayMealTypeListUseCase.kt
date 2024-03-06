package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.model.MealHistoryInfo
import com.chs.your_body_profile.domain.model.MealType
import com.chs.your_body_profile.domain.repository.FoodRepository
import java.time.LocalDate
import javax.inject.Inject

class GetDayMealTypeUseCase @Inject constructor(
    private val repository: FoodRepository
) {
    suspend operator fun invoke(
        takenDate: LocalDate,
        mealType: MealType
    ): MealHistoryInfo {
        return repository.getDayMealTypeList(
            takenDate = takenDate,
            mealType = mealType
        )
    }
}