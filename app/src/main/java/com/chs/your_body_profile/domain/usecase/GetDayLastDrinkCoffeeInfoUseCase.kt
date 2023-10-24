package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.model.DrinkType
import com.chs.your_body_profile.domain.repository.DrinkRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class GetDayLastDrinkCoffeeInfoUseCase @Inject constructor(
    private val repository: DrinkRepository
) {
    operator fun invoke(localDate: LocalDate): Flow<DrinkType.DrinkCoffeeInfo?> {
        return repository.getDayCoffeeInfo(localDate)
    }
}