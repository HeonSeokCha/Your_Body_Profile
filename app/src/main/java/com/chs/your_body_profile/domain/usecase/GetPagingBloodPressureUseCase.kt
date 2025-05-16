package com.chs.your_body_profile.domain.usecase

import androidx.paging.PagingData
import com.chs.your_body_profile.domain.model.BloodPressureInfo
import com.chs.your_body_profile.domain.repository.BloodPressureRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class GetPagingBloodPressureUseCase @Inject constructor(
    private val repository: BloodPressureRepository
) {
    operator fun invoke(): Flow<PagingData<Pair<LocalDate, List<BloodPressureInfo>>>> {
        return repository.getDayPagingInfo()
    }
}