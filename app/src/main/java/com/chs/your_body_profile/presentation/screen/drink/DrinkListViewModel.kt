package com.chs.your_body_profile.presentation.screen.drink

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import androidx.paging.cachedIn
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.domain.model.DrinkInfo
import com.chs.your_body_profile.domain.model.DrinkType
import com.chs.your_body_profile.domain.usecase.DeleteDrinkInfoUseCase
import com.chs.your_body_profile.domain.usecase.GetPagingDrinkUseCase
import com.chs.your_body_profile.presentation.Screens
import com.chs.your_body_profile.presentation.screen.bills.list.PayListEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class DrinkListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getPagingDrinkUseCase: GetPagingDrinkUseCase,
    private val deleteDrinkInfoUseCase: DeleteDrinkInfoUseCase
) : ViewModel() {

    private val type: DrinkType = savedStateHandle.toRoute<Screens.DrinkList>().drinkType
    private val _state: MutableStateFlow<DrinkListState> = MutableStateFlow(DrinkListState(type))
    val state = _state
        .onStart {
            initInfo()
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            DrinkListState(type)
        )

    private fun initInfo() {
        _state.update {
            it.copy(
                pagingData = getPagingDrinkUseCase.invoke(type).cachedIn(viewModelScope)
            )
        }
    }

    fun changeEvent(intent: DrinkListEvent) {
        when (intent) {
            is DrinkListEvent.OnChangeSelectIdx -> changeSelectIdx(intent.idx)
            is DrinkListEvent.OnSelectInfo -> changeSelectInfo(intent.info)

            DrinkListEvent.OnChangeShowDialog -> {
                _state.update { it.copy(showDialog = !it.showDialog) }
            }

            is DrinkListEvent.OnLongClickItem -> {
                _state.update {
                    it.copy(
                        selectRemoveInfo = intent.info,
                        showDialog = true
                    )
                }
            }

            DrinkListEvent.OnRemoveInfo -> {
                deleteInfo()
            }
            else -> Unit
        }
    }

    private fun changeSelectIdx(idx: Int) {
        _state.update {
            it.copy(selectIdx = idx)
        }
    }

    private fun changeSelectInfo(info: List<DrinkInfo>) {
        _state.update {
            it.copy(selectInfo = info)
        }
    }

    private fun deleteInfo() {

        if (_state.value.selectRemoveInfo == null) return
        viewModelScope.launch {
            deleteDrinkInfoUseCase(_state.value.selectRemoveInfo!!)
            _state.update {
                it.copy(
                    showDialog = false,
                    selectRemoveInfo = null,
                    selectInfo = emptyList()
                    )
            }

            initInfo()
        }
    }
}