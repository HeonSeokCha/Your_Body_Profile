package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.model.MedicineInfo
import com.chs.your_body_profile.domain.repository.MedicineRepository
import java.time.LocalDate
import javax.inject.Inject

class GetDayLastMedicineInfoUseCase @Inject constructor(
    private val repository: MedicineRepository
) {
    suspend operator fun invoke(localDate: LocalDate): MedicineInfo? =
        repository.getDayLastInfo(localDate)
}