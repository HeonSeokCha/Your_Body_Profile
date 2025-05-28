package com.chs.your_body_profile.domain.usecase

import androidx.paging.PagingData
import com.chs.your_body_profile.domain.model.DrinkInfo
import com.chs.your_body_profile.domain.model.DrinkType
import com.chs.your_body_profile.domain.repository.DrinkRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class GetPagingDrinkUseCase @Inject constructor(
    private val repository: DrinkRepository
) {
    operator fun invoke(drinkType: DrinkType): Flow<PagingData<Pair<LocalDate, List<DrinkInfo>>>> {
        return repository.getDayPagingInfoList(drinkType)
    }
}