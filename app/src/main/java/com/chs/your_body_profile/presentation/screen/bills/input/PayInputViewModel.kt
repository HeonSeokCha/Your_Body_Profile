package com.chs.your_body_profile.presentation.screen.bills.input

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.chs.your_body_profile.domain.model.PaymentInfo
import com.chs.your_body_profile.domain.usecase.UpsertPayInfoUseCase
import com.chs.your_body_profile.presentation.Screens
import com.chs.your_body_profile.presentation.screen.BaseEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class PayInputViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val upsertPayInfoUseCase: UpsertPayInfoUseCase
) : ViewModel() {

    private val lastInputAmount: Long = savedStateHandle.toRoute<Screens.PayInput>().info ?: 0

    private val _state = MutableStateFlow(PayInputState(amount = lastInputAmount))
    val state = _state.asStateFlow()

    private val _effect: Channel<BaseEffect> = Channel()
    val effect = _effect.receiveAsFlow()

    fun changeIntent(intent: PayInputEvent) {
        when (intent) {
            is PayInputEvent.ChangeDateTime -> updatePaymentTime(intent.dateTime)
            PayInputEvent.ChangeShowDateTimePicker -> {
                _state.update { it.copy(isShowDateTimePicker = !it.isShowDateTimePicker) }
            }

            PayInputEvent.OnBack -> TODO()
            is PayInputEvent.OnChangeMemo -> updateMemo(intent.memo)
            is PayInputEvent.OnChangePayAmount -> updateAmount(intent.amount)
            is PayInputEvent.OnChangePayTitle -> updateTitle(intent.title)
            is PayInputEvent.OnChangePaySubTitle -> updateSubTitle(intent.subTitle)
            PayInputEvent.OnClickSaveButton -> upsertPayInfo()
        }
    }

    private fun updatePaymentTime(time: LocalDateTime) {
        _state.update {
            it.copy(
                paymentTime = time,
                isShowDateTimePicker = false
            )
        }
    }

    private fun updateTitle(title: String) {
        _state.update { it.copy(title = title) }
    }
    private fun updateSubTitle(subTitle: String) {
        _state.update { it.copy(subTitle = subTitle) }
    }

    private fun updateAmount(amount: Long) {
        _state.update { it.copy(amount = amount) }
    }

    private fun updateMemo(text: String) {
        _state.update { it.copy(memo = text) }
    }

    private fun upsertPayInfo() {
        viewModelScope.launch {
            upsertPayInfoUseCase(
                PaymentInfo(
                    paymentTime = _state.value.paymentTime,
                    title = _state.value.title,
                    subTitle = _state.value.subTitle,
                    amount = _state.value.amount,
                    memo = _state.value.memo
                )
            )
            _effect.send(BaseEffect.OnBack)
        }
    }
}