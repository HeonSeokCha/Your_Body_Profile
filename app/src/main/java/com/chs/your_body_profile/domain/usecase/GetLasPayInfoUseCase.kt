package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.model.PaymentInfo
import com.chs.your_body_profile.domain.repository.PayInfoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLasPayInfoUseCase @Inject constructor(
    private val repository: PayInfoRepository
) {
    operator fun invoke(): Flow<PaymentInfo?> {
        return repository.getDayLastInfo()
    }
}