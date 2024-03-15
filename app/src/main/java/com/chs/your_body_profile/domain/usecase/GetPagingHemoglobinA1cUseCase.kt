package com.chs.your_body_profile.domain.usecase

import androidx.paging.PagingData
import com.chs.your_body_profile.domain.model.HemoglobinA1cInfo
import com.chs.your_body_profile.domain.repository.HemoglobinA1cRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class GetPagingHemoglobinA1cUseCase @Inject constructor(
    private val hemoglobinA1cRepository: HemoglobinA1cRepository
) {
    operator fun invoke(): Flow<PagingData<Pair<LocalDate, List<Int>>>> {
        return hemoglobinA1cRepository.getDayPagingInfo()
    }
}