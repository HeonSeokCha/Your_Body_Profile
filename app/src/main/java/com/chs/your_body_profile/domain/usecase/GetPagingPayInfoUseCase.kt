package com.chs.your_body_profile.domain.usecase

import androidx.paging.PagingData
import com.chs.your_body_profile.domain.model.PaymentInfo
import com.chs.your_body_profile.domain.repository.PayInfoRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class GetPagingPayInfoUseCase @Inject constructor(
    private val repository: PayInfoRepository
) {
    operator fun invoke(): Flow<PagingData<Pair<LocalDate, List<PaymentInfo>>>> {
        return repository.getDayPagingInfo()
    }
}