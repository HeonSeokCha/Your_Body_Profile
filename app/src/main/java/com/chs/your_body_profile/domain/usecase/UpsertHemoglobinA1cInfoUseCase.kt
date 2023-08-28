package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.data.mapper.toHemoglobinA1cInfoEntity
import com.chs.your_body_profile.domain.model.HemoglobinA1cInfo
import com.chs.your_body_profile.domain.repository.BodyRepository
import javax.inject.Inject

class UpsertHemoglobinA1cInfoUseCase @Inject constructor(
    private val repository: BodyRepository
) {
    suspend operator fun invoke(hemoglobinA1cInfo: HemoglobinA1cInfo) {
        repository.upsertInfo(hemoglobinA1cInfo.toHemoglobinA1cInfoEntity())
    }
}