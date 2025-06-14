package com.chs.your_body_profile.presentation.screen.bills.list

import com.chs.your_body_profile.domain.model.PaymentInfo

sealed class PayListEvent {
    data class OnChangeSelectIdx(val idx: Int) : PayListEvent()
    data class OnSelectInfo(val infoList: List<PaymentInfo>) : PayListEvent()
    data object OnClickInputButton : PayListEvent()
    data class OnLongClickItem(val info: PaymentInfo): PayListEvent()
    data object OnChangeShowDialog : PayListEvent()
    data object OnRemoveInfo : PayListEvent()
    data object OnBack : PayListEvent()
}