package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.model.BloodSugarInfo
import com.chs.your_body_profile.domain.repository.BloodSugarRepository
import javax.inject.Inject

class DeleteBloodSugarInfoUseCase @Inject constructor(
    private val repository: BloodSugarRepository
) {
    suspend operator fun invoke(info: BloodSugarInfo) {
        repository.deleteInfo(info)
    }
}