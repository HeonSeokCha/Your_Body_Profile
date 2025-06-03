package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.model.MedicineInfo
import com.chs.your_body_profile.domain.repository.MedicineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLastMedicineInfoUseCase @Inject constructor(
    private val repository: MedicineRepository
) {
    operator fun invoke(): Flow<MedicineInfo?> =
        repository.getDayLastInfo()
}