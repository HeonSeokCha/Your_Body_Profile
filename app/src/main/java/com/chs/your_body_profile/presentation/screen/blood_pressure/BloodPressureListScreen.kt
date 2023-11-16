package com.chs.your_body_profile.presentation.screen.blood_pressure

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController

@Composable
fun BloodPressureScreen(
    navController: NavHostController,
    viewModel: BloodPressureListViewModel = hiltViewModel()
) {
    val context: Context = LocalContext.current
    val state by viewModel.state.collectAsStateWithLifecycle()
}