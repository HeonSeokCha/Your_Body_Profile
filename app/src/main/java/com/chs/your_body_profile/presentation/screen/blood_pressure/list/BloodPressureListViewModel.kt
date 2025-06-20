package com.chs.your_body_profile.presentation.screen.blood_pressure.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.chs.your_body_profile.domain.model.BloodPressureInfo
import com.chs.your_body_profile.domain.usecase.DeleteBloodPressureInfoUseCase
import com.chs.your_body_profile.domain.usecase.GetPagingBloodPressureUseCase
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
class BloodPressureListViewModel @Inject constructor(
    private val getPagingBloodPressureUseCase: GetPagingBloodPressureUseCase,
    private val deleteBloodPressureInfoUseCase: DeleteBloodPressureInfoUseCase
): ViewModel() {

    private val _state: MutableStateFlow<BloodPressureListState> = MutableStateFlow(
        BloodPressureListState()
    )
    val state = _state
        .onStart {
            initPaging()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            BloodPressureListState()
        )


    private fun initPaging() {
        _state.update {
            it.copy(
                pagingList = getPagingBloodPressureUseCase()
                    .cachedIn(viewModelScope)
            )
        }
    }

    fun changeIntent(intent: BloodPressureListEvent) {
        when (intent) {
            is BloodPressureListEvent.OnChangeSelectIdx -> {
                _state.update {
                    it.copy(selectIdx = intent.idx)
                }
            }
            is BloodPressureListEvent.OnSelectInfo -> {
                selectInfo(intent.infoList)
            }

            BloodPressureListEvent.OnChangeShowDetailDialog -> {
                _state.update { it.copy(showDetailDialog = !it.showDetailDialog) }
            }

            BloodPressureListEvent.OnChangeShowRemoveDialog -> {
                _state.update { it.copy(showRemoveDialog = !it.showRemoveDialog) }
            }

            is BloodPressureListEvent.OnLongClickItem -> {
                _state.update {
                    it.copy(
                        selectInfo = intent.info,
                        showRemoveDialog = true
                    )
                }
            }

            is BloodPressureListEvent.OnClickItem -> {
                _state.update {
                    it.copy(
                        selectInfo = intent.info,
                        showDetailDialog = true
                    )
                }
            }

            BloodPressureListEvent.OnRemoveInfo -> {
                deleteInfo()
            }

            else -> Unit
        }
    }

    private fun selectInfo(infoList: List<BloodPressureInfo>) {
        _state.update { it.copy(selectListInfo = infoList) }
    }

    private fun deleteInfo() {
        if (state.value.selectInfo == null) return
        viewModelScope.launch {
            deleteBloodPressureInfoUseCase(_state.value.selectInfo!!)

            _state.update {
                it.copy(
                    showRemoveDialog = false,
                    selectInfo = null,
                    selectListInfo = emptyList()
                )
            }

            initPaging()
        }
    }
}