package com.chs.your_body_profile.presentation.screen.bills.list

import com.chs.your_body_profile.domain.model.PaymentInfo

sealed class PayListEvent {
    data class OnChangeSelectIdx(val idx: Int) : PayListEvent()
    data class OnSelectInfo(val infoList: List<PaymentInfo>) : PayListEvent()
    data object OnClickInputButton : PayListEvent()
    data object OnLongClickItem : PayListEvent()
    data object OnBack : PayListEvent()
}