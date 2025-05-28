package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.model.DrinkInfo
import com.chs.your_body_profile.domain.repository.DrinkRepository
import javax.inject.Inject

class UpsertDrinkInfoUseCase @Inject constructor(
    private val repository: DrinkRepository
) {
    suspend operator fun invoke(drinkInfo: DrinkInfo) {
        repository.upsertInfo(drinkInfo)
    }
}