package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.model.BloodSugarInfo
import com.chs.your_body_profile.domain.repository.BloodSugarRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class GetDayBloodSugarInfoList @Inject constructor(
    private val repository: BloodSugarRepository
) {
    operator fun invoke(localDate: LocalDate): Flow<List<BloodSugarInfo>> {
        return repository.getDayInfoList(localDate)
    }
}