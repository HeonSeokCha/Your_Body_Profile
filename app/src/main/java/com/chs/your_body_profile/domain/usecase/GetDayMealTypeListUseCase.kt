package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.model.FoodDetailInfo
import com.chs.your_body_profile.domain.model.MealHistoryInfo
import com.chs.your_body_profile.domain.repository.FoodRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class GetDayMealTypeListUseCase @Inject constructor(
    private val repository: FoodRepository
) {
    operator fun invoke(localDate: LocalDate): Flow<List<Pair<MealHistoryInfo, List<FoodDetailInfo>>>> {
        return repository.getDayMealTypeList(localDate)
    }
}