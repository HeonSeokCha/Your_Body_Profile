package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.model.BloodPressureInfo
import com.chs.your_body_profile.domain.repository.BloodPressureRepository
import javax.inject.Inject

class UpsertBloodPressureInfoUseCase @Inject constructor(
    private val repository: BloodPressureRepository
) {
    suspend operator fun invoke(bloodPressureInfo: BloodPressureInfo) {
        repository.upsertInfo(bloodPressureInfo)
    }
}