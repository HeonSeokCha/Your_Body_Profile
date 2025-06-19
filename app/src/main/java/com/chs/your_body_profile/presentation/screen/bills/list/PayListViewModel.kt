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
                selectListInfo(intent.infoList)
            }

            PayListEvent.OnChangeShowRemoveDialog -> {
                _state.update { it.copy(showRemoveDialog = !it.showRemoveDialog) }
            }


            PayListEvent.OnChangeShowDetailDialog -> {
                _state.update { it.copy(showDetailDialog = !it.showDetailDialog) }
            }

            is PayListEvent.OnClickItem -> {
                _state.update { it.copy(selectInfo = intent.info) }
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

    private fun selectListInfo(infoList: List<PaymentInfo>) {
        _state.update { it.copy(selectListInfo = infoList) }
    }

    private fun deleteInfo() {
        if (_state.value.selectInfo == null) return
        viewModelScope.launch {
            deletePayInfoUseCase(_state.value.selectInfo!!)
            _state.update {
                it.copy(
                    showRemoveDialog = false,
                    selectInfo = null,
                    selectListInfo = emptyList()
                )
            }

            getPagingPayInfo()
        }
    }
}