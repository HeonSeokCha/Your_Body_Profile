package com.chs.your_body_profile.domain.usecase

import androidx.paging.PagingData
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.domain.repository.DrinkRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class GetPagingDrinkWaterUseCase @Inject constructor(
    private val repository: DrinkRepository
) {
   operator fun invoke(): Flow<PagingData<Pair<LocalDate, Int>>> {
       return repository.getDayPagingInfoList(Constants.DRINK_TYPE_WATER)
   }
}