package com.chs.your_body_profile.presentation.screen.hemoglobinA1c

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.presentation.common.ItemDualNumberPicker

@Composable
fun HemoglobinA1cInputScreen(
    navController: NavHostController,
    viewModel: HemoglobinA1cInputViewModel = hiltViewModel()
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(
                    start = 8.dp,
                    end = 8.dp,
                    top = 16.dp,
                    bottom = 68.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ItemDualNumberPicker(
                title = "당화혈색소 (%)",
                firstItems = Constants.RANGE_HEMOGLOBIN_A1C_FIRST_RANGE.map { it },
                firstStartIdx = 6,
                secondItems = Constants.RANGE_HEMOGLOBIN_A1C_SECOND_RANGE.map { it },
                secondStartIdx = 5,
                onSelectItemValue = {
                    Log.e("TEST", it.toString())
                }, onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}