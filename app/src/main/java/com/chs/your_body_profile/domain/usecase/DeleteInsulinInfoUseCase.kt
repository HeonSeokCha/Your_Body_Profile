package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.model.InsulinInfo
import com.chs.your_body_profile.domain.repository.InsulinRepository
import javax.inject.Inject

class DeleteInsulinInfoUseCase @Inject constructor(
    private val repository: InsulinRepository
) {
    suspend operator fun invoke(info: InsulinInfo) {
        repository.deleteInfo(info)
    }
}