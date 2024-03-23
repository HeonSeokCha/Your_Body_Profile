package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.model.FoodDetailInfo
import com.chs.your_body_profile.domain.repository.FoodRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime
import javax.inject.Inject

class GetDayLastTakenFoodInfoUseCase @Inject constructor(
    private val repository: FoodRepository
) {
    operator fun invoke(time: LocalDateTime): Flow<FoodDetailInfo?> {
        return repository.getDayLastTakenFood(time)
    }
}