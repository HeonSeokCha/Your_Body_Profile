package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.model.WeightInfo
import com.chs.your_body_profile.domain.repository.WeightRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLastWeightInfoUseCase @Inject constructor(
    private val repository: WeightRepository
) {
    operator fun invoke(): Flow<WeightInfo?> =
        repository.getDayLastInfo()
}