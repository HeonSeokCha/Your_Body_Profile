package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.model.BloodSugarInfo
import com.chs.your_body_profile.domain.repository.BloodSugarRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLastBloodSugarInfoUseCase @Inject constructor(
    private val repository: BloodSugarRepository
) {
    operator fun invoke(): Flow<BloodSugarInfo?> {
        return repository.getDayLastInfo()
    }
}