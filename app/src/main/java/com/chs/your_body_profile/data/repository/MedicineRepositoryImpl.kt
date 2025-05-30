package com.chs.your_body_profile.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.chs.your_body_profile.common.toMillis
import com.chs.your_body_profile.data.mapper.toMedicineInfo
import com.chs.your_body_profile.data.mapper.toMedicineInfoEntity
import com.chs.your_body_profile.data.source.db.dao.MedicineDao
import com.chs.your_body_profile.data.source.paging.DayMedicinePaging
import com.chs.your_body_profile.domain.model.MedicineInfo
import com.chs.your_body_profile.domain.repository.MedicineRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

class MedicineRepositoryImpl @Inject constructor(
    private val medicineDao: MedicineDao
) : MedicineRepository {

    override fun getDayLastInfo(): Flow<MedicineInfo?> {
        return medicineDao.getDayLastInfo().map { it?.toMedicineInfo() }
    }

    override suspend fun upsertInfo(info: MedicineInfo) {
        medicineDao.upsert(info.toMedicineInfoEntity())
    }

    override suspend fun deleteInfo(info: MedicineInfo) {
        medicineDao.delete(info.toMedicineInfoEntity())
    }
}