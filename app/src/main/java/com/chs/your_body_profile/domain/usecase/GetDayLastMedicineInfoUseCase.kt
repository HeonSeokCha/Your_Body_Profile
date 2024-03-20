package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.model.MedicineInfo
import com.chs.your_body_profile.domain.repository.MedicineRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

class GetDayLastMedicineInfoUseCase @Inject constructor(
    private val repository: MedicineRepository
) {
    operator fun invoke(time: LocalDateTime): Flow<MedicineInfo?> =
        repository.getDayLastInfo(time)
}