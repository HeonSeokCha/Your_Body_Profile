package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.model.MealHistoryInfo
import com.chs.your_body_profile.domain.repository.FoodRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class GetDayTakenListUseCase @Inject constructor(
    private val repository: FoodRepository
) {
    suspend operator fun invoke(
        takenDate: LocalDate
    ): Flow<List<MealHistoryInfo>> {
        return repository.getDayTakenList(takenDate)
    }
}