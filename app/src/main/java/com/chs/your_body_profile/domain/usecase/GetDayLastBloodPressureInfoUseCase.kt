package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.model.BloodPressureInfo
import com.chs.your_body_profile.domain.repository.BodyRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime
import javax.inject.Inject

class GetDayLastBloodPressureInfoUseCase @Inject constructor(
    private val bodyRepository: BodyRepository
) {
    operator fun invoke(localDateTime: LocalDateTime): Flow<BloodPressureInfo?> {
        return bodyRepository.getDayLastBloodPressureInfo(localDateTime)
    }
}