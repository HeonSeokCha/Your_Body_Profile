package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.model.DrinkCoffeeInfo
import com.chs.your_body_profile.domain.repository.BodyRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class GetDayLastDrinkCoffeeInfoUseCase @Inject constructor(
    private val repository: BodyRepository
) {
    operator fun invoke(localDate: LocalDate): Flow<DrinkCoffeeInfo?> {
        return repository.getDayLastDrinkCoffeeInfo(localDate)
    }
}