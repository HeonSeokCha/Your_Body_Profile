package com.chs.your_body_profile.presentation.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    if (state.isLoading) {
        CircularProgressIndicator()
    }

    LazyColumn {
        items(state.bodyMeasureList) {
            HomeBaseInfoCard(
                title = it.title,
                infoValue = "",
                infoUnit = it.unit,
                subComposable = {
                    Button(onClick = { viewModel.updateBodyMeasureModifyTime(it)}) {
                        Text("입력")
                    }
                }
            ) {

            }
        }
    }
}