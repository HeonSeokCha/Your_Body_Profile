package com.chs.your_body_profile.presentation.screen.bills.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.chs.your_body_profile.domain.model.PaymentInfo
import com.chs.your_body_profile.domain.usecase.DeletePayInfoUseCase
import com.chs.your_body_profile.domain.usecase.GetPagingPayInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PayListViewModel @Inject constructor(
    private val getPagingPayInfoUseCase: GetPagingPayInfoUseCase,
    private val deletePayInfoUseCase: DeletePayInfoUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(PayListState())
    val state = _state
        .onStart {
            getPagingPayInfo()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            PayListState()
        )

    fun changeIntent(intent: PayListEvent) {
        when (intent) {
            is PayListEvent.OnChangeSelectIdx -> {
                _state.update {
                    it.copy(selectIdx = intent.idx)
                }
            }
            is PayListEvent.OnSelectInfo -> {
                selectInfo(intent.infoList)
            }

            PayListEvent.OnChangeShowDialog -> {
                _state.update { it.copy(showDialog = !it.showDialog) }
            }

            is PayListEvent.OnLongClickItem -> {
                _state.update {
                    it.copy(
                        selectRemoveInfo = intent.info,
                        showDialog = true
                    )
                }
            }

            PayListEvent.OnRemoveInfo -> {
                deleteInfo()
            }

            else -> Unit
        }
    }

    private fun getPagingPayInfo() {
        _state.update {
            it.copy(
                pagingList = getPagingPayInfoUseCase()
                    .cachedIn(viewModelScope)
            )
        } }

    private fun selectInfo(infoList: List<PaymentInfo>) {
        _state.update { it.copy(selectInfo = infoList) }
    }

    private fun deleteInfo() {
        if (_state.value.selectRemoveInfo == null) return
        viewModelScope.launch {
            deletePayInfoUseCase(_state.value.selectRemoveInfo!!)
            _state.update {
                it.copy(
                    showDialog = false,
                    selectRemoveInfo = null,
                    selectInfo = emptyList()
                )
            }

            getPagingPayInfo()
        }
    }
}