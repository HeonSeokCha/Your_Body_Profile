package com.chs.your_body_profile.presentation.screen.bills.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.chs.your_body_profile.domain.model.PaymentInfo
import com.chs.your_body_profile.domain.usecase.GetPagingPayInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PayListViewModel @Inject constructor(
    private val getPagingPayInfoUseCase: GetPagingPayInfoUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(PayListState())
    val state = _state.asStateFlow()

    init {
        getPagingPayInfo()
    }


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

            else -> Unit
        }
    }

    private fun getPagingPayInfo() {
        _state.update {
            it.copy(
                pagingList = getPagingPayInfoUseCase()
                    .cachedIn(viewModelScope)
            )
        }
    }

    private fun selectInfo(infoList: List<PaymentInfo>) {
        _state.update { it.copy(selectInfo = infoList) }
    }
}