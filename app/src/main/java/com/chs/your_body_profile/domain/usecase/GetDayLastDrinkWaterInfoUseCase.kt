package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.model.DrinkWaterInfo
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class GetDayLastDrinkWaterInfoUseCase @Inject constructor(
    private val repository: BodyRepository
) {
    operator fun invoke(localDate: LocalDate): Flow<DrinkWaterInfo?> =
        repository.getDayLastDrinkWaterInfo(localDate)
}