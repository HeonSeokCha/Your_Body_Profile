package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.model.DrinkCoffeeInfo
import com.chs.your_body_profile.domain.repository.BodyRepository
import javax.inject.Inject

class UpsertDrinkCoffeeInfoUseCase @Inject constructor(
    private val repository: BodyRepository
) {
    suspend operator fun invoke(drinkCoffeeInfo: DrinkCoffeeInfo) {
        repository.upsertInfo(drinkCoffeeInfo)
    }
}