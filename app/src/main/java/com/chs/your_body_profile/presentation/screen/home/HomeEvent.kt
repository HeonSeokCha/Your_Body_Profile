package com.chs.your_body_profile.presentation.screen.home

import com.chs.your_body_profile.presentation.Screens

sealed class HomeEvent {
    data class Navigate(val target: Screens) : HomeEvent()
    sealed class Update : HomeEvent() {
        sealed class Up : HomeEvent() {
            data object Water : Update()
            data object Coffee : Update()
        }

        sealed class Down : HomeEvent() {
            data object Water : Update()
            data object Coffee : Update()
        }
    }
}