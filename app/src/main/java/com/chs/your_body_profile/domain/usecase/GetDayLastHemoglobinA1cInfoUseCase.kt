package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.model.HemoglobinA1cInfo
import com.chs.your_body_profile.domain.repository.HemoglobinA1cRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class GetDayLastHemoglobinA1cInfoUseCase @Inject constructor(
    private val repository: HemoglobinA1cRepository
) {
    operator fun invoke(localDate: LocalDate): Flow<HemoglobinA1cInfo?> =
        repository.getDayLastInfo(localDate)
}