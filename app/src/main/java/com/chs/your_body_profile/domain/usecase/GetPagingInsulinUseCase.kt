package com.chs.your_body_profile.domain.usecase

import androidx.paging.PagingData
import com.chs.your_body_profile.domain.model.InsulinInfo
import com.chs.your_body_profile.domain.repository.InsulinRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class GetPagingInsulinUseCase @Inject constructor(
    private val repository: InsulinRepository
) {
    operator fun invoke(): Flow<PagingData<Pair<LocalDate, List<InsulinInfo>>>> {
        return repository.getDayPagingInfo()
    }
}