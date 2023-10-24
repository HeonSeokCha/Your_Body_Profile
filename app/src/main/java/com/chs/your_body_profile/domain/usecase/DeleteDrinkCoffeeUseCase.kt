package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.model.DrinkType
import com.chs.your_body_profile.domain.repository.DrinkRepository
import javax.inject.Inject

class DeleteDrinkCoffeeUseCase @Inject constructor(
    private val repository: DrinkRepository
) {
    suspend operator fun invoke(info: DrinkType.DrinkCoffeeInfo) {
        repository.deleteInfo(info)
    }
}