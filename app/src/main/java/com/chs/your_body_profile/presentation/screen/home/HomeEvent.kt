package com.chs.your_body_profile.presentation.screen.home

import com.chs.your_body_profile.presentation.Screens

sealed class HomeEvent {
    data class Navigate(val target: Screens) : HomeEvent()
    sealed class Update : HomeEvent() {
        data class Water(val cups: Int) : Update()
        data class Coffee(val cups: Int) : Update()
    }
}