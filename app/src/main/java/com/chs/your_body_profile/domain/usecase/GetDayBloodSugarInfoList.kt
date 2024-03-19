package com.chs.your_body_profile.domain.usecase

import com.chs.your_body_profile.domain.model.BloodSugarInfo
import com.chs.your_body_profile.domain.repository.BloodSugarRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class GetDayBloodSugarInfoList @Inject constructor(
    private val repository: BloodSugarRepository
) {
    suspend operator fun invoke(localDate: LocalDate): List<BloodSugarInfo> {
        return repository.getDayInfoList(localDate)
    }
}