package com.chs.your_body_profile.presentation.screen.hemoglobinA1c.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.chs.your_body_profile.domain.model.HemoglobinA1cInfo
import com.chs.your_body_profile.domain.usecase.DeleteHemoglobinA1cInfoUseCase
import com.chs.your_body_profile.domain.usecase.GetPagingHemoglobinA1cUseCase
import com.chs.your_body_profile.presentation.screen.bills.list.PayListEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HemoglobinA1cListViewModel @Inject constructor(
    private val getPagingHemoglobinA1cUseCase: GetPagingHemoglobinA1cUseCase,
    private val deleteHemoglobinA1cInfoUseCase: DeleteHemoglobinA1cInfoUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HemoglobinA1cListState())
    val state = _state
        .onStart {
            getPagingInfo()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            HemoglobinA1cListState()
        )

    init {
        getPagingInfo()
    }

    fun changeIntent(intent: HemoglobinA1cListEvent) {
        when (intent) {
            is HemoglobinA1cListEvent.OnChangeSelectIdx -> changeIdx(intent.idx)
            is HemoglobinA1cListEvent.OnSelectInfo -> selectInfo(intent.infoList)

            HemoglobinA1cListEvent.OnChangeShowDialog -> {
                _state.update { it.copy(showDialog = !it.showDialog) }
            }

            is HemoglobinA1cListEvent.OnLongClickItem -> {
                _state.update {
                    it.copy(
                        selectRemoveInfo = intent.info,
                        showDialog = true
                    )
                }
            }

            HemoglobinA1cListEvent.OnRemoveInfo -> {
                deleteInfo()
            }

            else -> Unit
        }
    }

    private fun changeIdx(idx: Int) {
        _state.update {
            it.copy(selectIdx = idx)
        }
    }

    private fun selectInfo(info: List<HemoglobinA1cInfo>) {
        _state.update {
            it.copy(selectInfo = info)
        }
    }

    private fun getPagingInfo() {
        _state.update {
            it.copy(pagingList = getPagingHemoglobinA1cUseCase().cachedIn(viewModelScope))
        }
    }

    private fun deleteInfo() {
        if (_state.value.selectRemoveInfo == null) return
        viewModelScope.launch {
            deleteHemoglobinA1cInfoUseCase(_state.value.selectRemoveInfo!!)
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