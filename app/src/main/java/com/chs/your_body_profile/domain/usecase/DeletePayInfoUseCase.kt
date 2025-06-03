package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.model.PaymentInfo
import com.chs.your_body_profile.domain.repository.PayInfoRepository
import javax.inject.Inject

class DeletePayInfoUseCase @Inject constructor(
    private val repository: PayInfoRepository
) {
    suspend operator fun invoke(info: PaymentInfo) {
        repository.deleteInfo(info)
    }
}