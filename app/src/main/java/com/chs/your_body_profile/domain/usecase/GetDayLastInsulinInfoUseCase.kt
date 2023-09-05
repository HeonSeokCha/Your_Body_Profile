package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.model.InsulinInfo
import com.chs.your_body_profile.domain.repository.InsulinRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class GetDayLastInsulinInfoUseCase @Inject constructor(
    private val repository: InsulinRepository
) {
    operator fun invoke(localDate: LocalDate): Flow<InsulinInfo?> =
        repository.getDayLastInfo(localDate)
}