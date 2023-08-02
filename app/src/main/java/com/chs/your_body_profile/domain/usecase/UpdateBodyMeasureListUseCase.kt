package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.model.BodyMeasureInfo
import com.chs.your_body_profile.domain.repository.BodyRepository
import javax.inject.Inject

class UpdateBodyMeasureListUseCase @Inject constructor(
    private val repository: BodyRepository
) {
    suspend operator fun invoke(bodyMeasureInfo: BodyMeasureInfo) {
        repository.updateBodyMeasureInfo(bodyMeasureInfo)
    }
}