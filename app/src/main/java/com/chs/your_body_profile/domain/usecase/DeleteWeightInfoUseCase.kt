package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.model.WeightInfo
import com.chs.your_body_profile.domain.repository.WeightRepository
import javax.inject.Inject

class DeleteWeightInfoUseCase @Inject constructor(
    private val repository: WeightRepository
) {
    suspend operator fun invoke(info: WeightInfo) {
        repository.deleteInfo(info)
    }
}