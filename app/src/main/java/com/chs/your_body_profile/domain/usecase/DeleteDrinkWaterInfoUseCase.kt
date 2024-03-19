package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.model.DrinkCoffeeInfo
import com.chs.your_body_profile.domain.repository.DrinkRepository
import javax.inject.Inject

class DeleteDrinkWaterInfoUseCase @Inject constructor(
    private val repository: DrinkRepository
) {
    suspend operator fun invoke(info: DrinkCoffeeInfo) {
        repository.deleteInfo(info)
    }
}
