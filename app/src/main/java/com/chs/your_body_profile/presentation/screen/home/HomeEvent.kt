package com.chs.your_body_profile.presentation.screen.home

sealed class HomeEvent {
    sealed class Click : HomeEvent() {
        data object BloodPressure : Click()
        data object BloodSugar : Click()
        data object Insulin : Click()
        data object HemoglobinA1c : Click()
        data object Water : Click()
        data object Coffee : Click()
        data object Medicine : Click()
        data object Weight : Click()
        data object Food : Click()
    }

    sealed class Update : HomeEvent() {
        data class Water(val cups: Int) : Update()
        data class Coffee(val cups: Int) : Update()
    }
}