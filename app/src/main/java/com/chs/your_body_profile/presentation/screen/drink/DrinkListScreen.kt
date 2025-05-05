package com.chs.your_body_profile.presentation.screen.drink

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun DrinkListScreenRoot(
    viewModel: DrinkViewModel,
    onBack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    DrinkListScreen(state = state) { event ->
        when (event) {

        }
    }
}

@Composable
fun DrinkListScreen(
    state: DrinkState,
    onEvent: (DrinkListEvent) -> Unit
) {

}