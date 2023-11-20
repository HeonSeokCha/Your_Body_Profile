package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.model.FoodDetailInfo
import com.chs.your_body_profile.domain.model.MealHistoryInfo
import com.chs.your_body_profile.domain.model.MealType
import com.chs.your_body_profile.domain.repository.FoodRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class GetDayMealTypeListUseCase @Inject constructor(
    private val repository: FoodRepository
) {
    operator fun invoke(
        takenDate: LocalDate,
        mealType: MealType
    ): Flow<Pair<MealHistoryInfo?, List<FoodDetailInfo>>> {
        return repository.getDayMealTypeList(
            takenDate = takenDate,
            mealType = mealType
        )
    }
}