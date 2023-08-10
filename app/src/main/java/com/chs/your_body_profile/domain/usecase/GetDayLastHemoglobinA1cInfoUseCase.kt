package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.model.HemoglobinA1cInfo
import com.chs.your_body_profile.domain.repository.BodyRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class GetDayLastHemoglobinA1cInfoUseCase @Inject constructor(
    private val repository: BodyRepository
) {
    operator fun invoke(localDate: LocalDate): Flow<HemoglobinA1cInfo?> =
        repository.getDayLastHemoglobinA1cInfo(localDate)
}