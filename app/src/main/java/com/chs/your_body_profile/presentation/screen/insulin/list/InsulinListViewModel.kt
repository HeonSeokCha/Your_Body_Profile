package com.chs.your_body_profile.presentation.screen.insulin.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.chs.your_body_profile.domain.model.InsulinInfo
import com.chs.your_body_profile.domain.usecase.DeleteInsulinInfoUseCase
import com.chs.your_body_profile.domain.usecase.GetPagingInsulinUseCase
import com.chs.your_body_profile.presentation.screen.bills.list.PayListEvent
import com.chs.your_body_profile.presentation.screen.blood_pressure.list.BloodPressureListEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InsulinListViewModel @Inject constructor(
    private val getPagingInsulinUseCase: GetPagingInsulinUseCase,
    private val deleteInsulinInfoUseCase: DeleteInsulinInfoUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<InsulinListState> = MutableStateFlow(InsulinListState())
    val state: StateFlow<InsulinListState> =
        _state.onStart { getPagingInfo() }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(),
                InsulinListState()
            )

    fun changeIntent(intent: InsulinListEvent) {
        when (intent) {
            is InsulinListEvent.OnChangeSelectIdx -> {
                _state.update {
                    it.copy(selectIdx = intent.idx)
                }
            }

            is InsulinListEvent.OnSelectInfo -> {
                selectInfo(intent.infoList)
            }


            InsulinListEvent.OnChangeShowDialog -> {
                _state.update { it.copy(showDialog = !it.showDialog) }
            }

            is InsulinListEvent.OnLongClickItem -> {
                _state.update {
                    it.copy(
                        selectRemoveInfo = intent.info,
                        showDialog = true
                    )
                }
            }

            InsulinListEvent.OnRemoveInfo -> {
                deleteInfo()
            }

            else -> Unit
        }
    }

    private fun selectInfo(info: List<InsulinInfo>) {
        _state.update { it.copy(selectInfo = info) }
    }

    private fun getPagingInfo() {
        _state.update {
            it.copy(pagingList = getPagingInsulinUseCase().cachedIn(viewModelScope))
        }
    }

    private fun deleteInfo() {
        if (_state.value.selectRemoveInfo == null) return
        viewModelScope.launch {
            deleteInsulinInfoUseCase(_state.value.selectRemoveInfo!!)
            _state.update {
                it.copy(
                    showDialog = false,
                    selectRemoveInfo = null,
                    selectInfo = emptyList()
                )
            }

            getPagingInfo()
        }
    }
}