package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.model.HemoglobinA1cInfo
import com.chs.your_body_profile.domain.repository.HemoglobinA1cRepository
import javax.inject.Inject

class DeleteHemoglobinA1cInfoUseCase @Inject constructor(
    private val repository: HemoglobinA1cRepository
) {
    suspend operator fun invoke(info: HemoglobinA1cInfo) {
        repository.deleteInfo(info)
    }
}