package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.model.DrinkCoffeeInfo
import com.chs.your_body_profile.domain.repository.DrinkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

class GetDayLastDrinkCoffeeInfoUseCase @Inject constructor(
    private val repository: DrinkRepository
) {
    operator fun invoke(localDate: LocalDate): Flow<DrinkCoffeeInfo?> {
        return repository.getDayCoffeeInfo(localDate)
    }
}