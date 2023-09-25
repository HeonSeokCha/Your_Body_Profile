package com.chs.your_body_profile.presentation.screen.blood_sugar

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class BloodSugarListViewModel @Inject constructor(

) : ViewModel() {
    private val _state = MutableStateFlow(BloodSugarListState())
    val state = _state.asStateFlow()


}