package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.repository.DrinkRepository
import javax.inject.Inject

class GetPagingDrinkCoffeeUseCase @Inject constructor(
    private val repository: DrinkRepository
) {
    operator fun invoke() {
        repository.getDayPagingInfoList()
    }
}