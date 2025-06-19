package com.chs.your_body_profile.presentation.screen.bills.list

import com.chs.your_body_profile.domain.model.PaymentInfo

sealed class PayListEvent {
    data class OnChangeSelectIdx(val idx: Int) : PayListEvent()
    data class OnSelectInfo(val infoList: List<PaymentInfo>) : PayListEvent()
    data object OnClickInputButton : PayListEvent()
    data class OnClickItem(val info: PaymentInfo): PayListEvent()
    data object OnChangeShowRemoveDialog : PayListEvent()
    data object OnChangeShowDetailDialog : PayListEvent()
    data object OnRemoveInfo : PayListEvent()
    data object OnBack : PayListEvent()
}