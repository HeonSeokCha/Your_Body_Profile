package com.chs.your_body_profile.presentation.screen.bills.input

import java.time.LocalDateTime

sealed class PayInputEvent {
    data object ChangeShowDateTimePicker : PayInputEvent()
    data class ChangeDateTime(val dateTime: LocalDateTime) : PayInputEvent()
    data class OnChangePayAmount(val amount: Long) : PayInputEvent()
    data class OnChangePayTitle(val title: String) : PayInputEvent()
    data class OnChangePaySubTitle(val subTitle: String) : PayInputEvent()
    data class OnChangeMemo(val memo: String): PayInputEvent()
    data object OnClickSaveButton : PayInputEvent()
    data object OnBack : PayInputEvent()
}