package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.model.DrinkWaterInfo
import com.chs.your_body_profile.domain.repository.DrinkRepository
import javax.inject.Inject

class UpsertDrinkWaterInfoUseCase @Inject constructor(
    private val repository: DrinkRepository
) {
    suspend operator fun invoke(drinkCoffeeInfo: DrinkWaterInfo) {
        repository.upsertInfo(drinkCoffeeInfo)
    }
}
