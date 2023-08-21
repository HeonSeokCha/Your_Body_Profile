package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.model.BloodSugarInfo
import com.chs.your_body_profile.domain.repository.BodyRepository
import javax.inject.Inject

class UpsertBloodSugarInfoUseCase @Inject constructor(
    private val repository: BodyRepository
) {
    suspend operator fun invoke(bloodSugarInfo: BloodSugarInfo) {
        repository.upsertInfo(bloodSugarInfo)
    }
}