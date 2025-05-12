package com.chs.your_body_profile.presentation.screen

sealed class BaseEffect {
    data object Idle : BaseEffect()
    data object OnBack : BaseEffect()
    data class ShowToast(val message: String) : BaseEffect()
}