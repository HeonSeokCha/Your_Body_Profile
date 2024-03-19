package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.model.DrinkWaterInfo
import com.chs.your_body_profile.domain.repository.DrinkRepository
import java.time.LocalDate
import javax.inject.Inject

class GetDayLastDrinkWaterInfoUseCase @Inject constructor(
    private val repository: DrinkRepository
) {
    suspend operator fun invoke(localDate: LocalDate): DrinkWaterInfo? {
        return repository.getDayWaterInfo(localDate)
    }
}