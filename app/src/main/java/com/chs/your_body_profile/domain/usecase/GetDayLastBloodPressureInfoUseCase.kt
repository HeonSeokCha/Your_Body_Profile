package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.model.BloodPressureInfo
import com.chs.your_body_profile.domain.repository.BloodPressureRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class GetDayLastBloodPressureInfoUseCase @Inject constructor(
    private val bodyRepository: BloodPressureRepository
) {
    operator fun invoke(localDate: LocalDate): Flow<BloodPressureInfo?> {
        return bodyRepository.getDayLastInfo(localDate)
    }
}