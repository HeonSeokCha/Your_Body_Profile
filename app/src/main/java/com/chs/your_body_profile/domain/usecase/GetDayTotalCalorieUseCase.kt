package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.repository.FoodRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class GetDayTotalCalorieUseCase @Inject constructor(
    private val repository: FoodRepository
) {
    operator fun invoke(time: LocalDate): Flow<Int> {
        return repository.getDayTotalCalories(time)
    }
}