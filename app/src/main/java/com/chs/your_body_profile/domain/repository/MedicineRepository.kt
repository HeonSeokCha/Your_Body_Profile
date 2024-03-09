package com.chs.your_body_profile.domain.repository

import androidx.paging.PagingData
import com.chs.your_body_profile.domain.model.MedicineInfo
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface MedicineRepository : BaseInfoRepository<MedicineInfo> {

    suspend fun getDayPagingList(): Flow<PagingData<Pair<LocalDate, List<MedicineInfo>>>>
}