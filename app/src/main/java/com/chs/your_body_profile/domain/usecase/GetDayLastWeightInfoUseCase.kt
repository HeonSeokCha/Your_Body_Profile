package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.model.WeightInfo
import com.chs.your_body_profile.domain.repository.WeightRepository
import java.time.LocalDate
import javax.inject.Inject

class GetDayLastWeightInfoUseCase @Inject constructor(
    private val repository: WeightRepository
) {
    suspend operator fun invoke(localDate: LocalDate): WeightInfo? =
        repository.getDayLastInfo(localDate)
}