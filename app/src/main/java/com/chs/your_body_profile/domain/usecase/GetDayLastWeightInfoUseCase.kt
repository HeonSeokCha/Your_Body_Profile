package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.model.WeightInfo
import com.chs.your_body_profile.domain.repository.WeightRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class GetDayLastWeightInfoUseCase @Inject constructor(
    private val repository: WeightRepository
) {
    operator fun invoke(localDate: LocalDate): Flow<WeightInfo?> =
        repository.getDayLastInfo(localDate)
}