package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.model.MealType
import com.chs.your_body_profile.domain.repository.FoodRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject
import kotlin.math.roundToInt

class GetDayMealTypeCaloriesUseCase @Inject constructor(
    private val repository: FoodRepository
) {
    suspend operator fun invoke(
        localDate: LocalDate,
        mealType: MealType
    ): Int {
        return repository.getDayMealTypeList(
            takenDate = localDate,
            mealType = mealType
        ).foodList.sumOf { it.calorie.roundToInt() }
    }
}