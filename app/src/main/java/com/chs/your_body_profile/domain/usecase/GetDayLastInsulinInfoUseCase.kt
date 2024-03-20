package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.model.InsulinInfo
import com.chs.your_body_profile.domain.repository.InsulinRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

class GetDayLastInsulinInfoUseCase @Inject constructor(
    private val repository: InsulinRepository
) {
    operator fun invoke(time: LocalDateTime): Flow<InsulinInfo?> =
        repository.getDayLastInfo(time)
}