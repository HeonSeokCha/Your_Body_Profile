package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.model.MealType
import com.chs.your_body_profile.domain.repository.FoodRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class GetDayMealTypeCarbohydrateUseCase @Inject constructor(
    private val repository: FoodRepository
) {
    operator fun invoke(
        localDate: LocalDate,
        mealType: MealType
    ): Flow<Double> {
        return repository.getMealTypeTotalCarbohydrate(localDate, mealType)
    }
}