package com.chs.your_body_profile.presentation.screen.blood_sugar.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.chs.your_body_profile.domain.model.BloodSugarInfo
import com.chs.your_body_profile.domain.usecase.DeleteBloodSugarInfoUseCase
import com.chs.your_body_profile.domain.usecase.GetPagingBloodSugarUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BloodSugarListViewModel @Inject constructor(
    private val getPagingBloodSugarUseCase: GetPagingBloodSugarUseCase,
    private val deleteBloodSugarInfoUseCase: DeleteBloodSugarInfoUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(BloodSugarListState())
    val state = _state
        .onStart {
            viewModelScope.launch {
                getPagingBloodSugar()
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            BloodSugarListState()
        )

    fun changeIntent(intent: BloodSugarListEvent) {
        when (intent) {
            is BloodSugarListEvent.OnChangeSelectIdx -> {
                _state.update {
                    it.copy(selectIdx = intent.idx)
                }
            }

            is BloodSugarListEvent.OnSelectInfo -> {
                selectInfo(intent.infoList)
            }


            BloodSugarListEvent.OnChangeShowDialog -> {
                _state.update { it.copy(showDialog = !it.showDialog) }
            }

            is BloodSugarListEvent.OnLongClickItem -> {
                _state.update {
                    it.copy(
                        selectRemoveInfo = intent.info,
                        showDialog = true
                    )
                }
            }

            BloodSugarListEvent.OnRemoveInfo -> {
                deleteInfo()
            }

            else -> Unit
        }
    }

    private fun getPagingBloodSugar() {
        _state.update {
            it.copy(
                pagingList = getPagingBloodSugarUseCase()
                    .cachedIn(viewModelScope)
            )
        }
    }

    private fun selectInfo(infoList: List<BloodSugarInfo>) {
        _state.update { it.copy(selectInfo = infoList) }
    }

    private fun deleteInfo() {
        if (_state.value.selectRemoveInfo == null) return
        viewModelScope.launch {
            deleteBloodSugarInfoUseCase(_state.value.selectRemoveInfo!!)
            _state.update {
                it.copy(
                    showDialog = false,
                    selectRemoveInfo = null,
                    selectInfo = emptyList()
                )
            }

            getPagingBloodSugar()
        }
    }
}