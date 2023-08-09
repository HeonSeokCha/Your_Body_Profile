package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.model.BloodSugarInfo
import com.chs.your_body_profile.domain.repository.BodyRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class GetDayLastBloodSugarInfoUseCase @Inject constructor(
    private val repository: BodyRepository
) {
    operator fun invoke(localDate: LocalDate): Flow<BloodSugarInfo?> {
        return repository.getDayLastBloodSugarInfo(localDate)
    }
}