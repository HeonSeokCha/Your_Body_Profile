package com.chs.your_body_profile.presentation.screen.insulin

sealed class InsulinInputEvent {
    data object OnBack : InsulinInputEvent()
}