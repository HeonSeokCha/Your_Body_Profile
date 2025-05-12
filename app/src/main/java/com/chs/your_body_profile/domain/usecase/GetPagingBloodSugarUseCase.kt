package com.chs.your_body_profile.domain.usecase

import androidx.paging.PagingData
import com.chs.your_body_profile.domain.repository.BloodSugarRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class GetPagingBloodSugarUseCase @Inject constructor(
    private val repository: BloodSugarRepository
) {
    operator fun invoke(): Flow<PagingData<Pair<LocalDate, Int>>> {
        return repository.getDayPagingInfo()
    }
}