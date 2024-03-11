package com.chs.your_body_profile.domain.usecase

import androidx.paging.PagingData
import com.chs.your_body_profile.domain.model.MedicineInfo
import com.chs.your_body_profile.domain.repository.MedicineRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class GetPagingMedicineUseCase @Inject constructor(
    private val repository: MedicineRepository
) {
   operator fun invoke(): Flow<PagingData<Pair<LocalDate, List<MedicineInfo>>>> {
       return repository.getDayPagingList()
   }
}