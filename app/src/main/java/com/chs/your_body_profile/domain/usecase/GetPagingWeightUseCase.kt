package com.chs.your_body_profile.domain.usecase

import androidx.paging.PagingData
import com.chs.your_body_profile.domain.model.WeightInfo
import com.chs.your_body_profile.domain.repository.WeightRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class GetPagingWeightUseCase @Inject constructor(
    private val repository: WeightRepository
) {
    operator fun invoke(): Flow<PagingData<Pair<LocalDate, List<WeightInfo>>>> {
        return repository.getDayPagingInfo()
    }
}