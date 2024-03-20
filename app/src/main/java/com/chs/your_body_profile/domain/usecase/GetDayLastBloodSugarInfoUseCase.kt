package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.model.BloodSugarInfo
import com.chs.your_body_profile.domain.repository.BloodSugarRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

class GetDayLastBloodSugarInfoUseCase @Inject constructor(
    private val repository: BloodSugarRepository
) {
    operator fun invoke(time: LocalDateTime): Flow<BloodSugarInfo?> {
        return repository.getDayLastInfo(time)
    }
}