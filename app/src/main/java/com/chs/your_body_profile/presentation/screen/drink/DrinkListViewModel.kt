package com.chs.your_body_profile.presentation.screen.drink

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import androidx.paging.cachedIn
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.domain.model.DrinkInfo
import com.chs.your_body_profile.domain.model.DrinkType
import com.chs.your_body_profile.domain.usecase.GetPagingDrinkUseCase
import com.chs.your_body_profile.presentation.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

@HiltViewModel
class DrinkListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getPagingDrinkUseCase: GetPagingDrinkUseCase,
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
}