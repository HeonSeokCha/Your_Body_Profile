package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.model.InsulinInfo
import com.chs.your_body_profile.domain.repository.InsulinRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLastInsulinInfoUseCase @Inject constructor(
    private val repository: InsulinRepository
) {
    operator fun invoke(): Flow<InsulinInfo?> =
        repository.getDayLastInfo()
}