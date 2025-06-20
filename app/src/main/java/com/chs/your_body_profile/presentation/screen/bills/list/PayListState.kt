package com.chs.your_body_profile.presentation.screen.bills.list

import androidx.paging.PagingData
import com.chs.your_body_profile.domain.model.PaymentInfo
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

data class PayListState(
    val pagingList: Flow<PagingData<Pair<LocalDate, List<PaymentInfo>>>>? = null,
    val selectIdx: Int = 0,
    val selectListInfo: List<PaymentInfo> = emptyList(),
    val selectInfo: PaymentInfo? = null,
    val showRemoveDialog: Boolean = false,
    val showDetailDialog: Boolean = false
)