package com.chs.your_body_profile.presentation.screen.weight.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun WeightListScreenRoot(
    viewModel: WeightListViewModel,
    onInput: () -> Unit,
    onBack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    WeightListScreen(state) {

    }
}

@Composable
fun WeightListScreen(
    state: WeightListState,
    onIntent: (WeightListEvent) -> Unit
) {

}