package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.model.WeightInfo
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class GetDayLastWeightInfoUseCase @Inject constructor(
    private val repository: B
) {
    operator fun invoke(localDate: LocalDate): Flow<WeightInfo?> =
        repository.getDayLastWeightInfo(localDate)
}