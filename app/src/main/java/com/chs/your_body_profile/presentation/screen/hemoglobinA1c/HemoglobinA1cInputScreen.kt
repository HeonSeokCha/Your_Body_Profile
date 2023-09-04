package com.chs.your_body_profile.presentation.screen.hemoglobinA1c

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.presentation.common.ItemDualNumberPicker
import com.chs.your_body_profile.presentation.common.ItemInputBottomMenu
import com.chs.your_body_profile.presentation.common.ItemSmallInputText

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

            Spacer(modifier = Modifier.height(16.dp))

            ItemDualNumberPicker(
                title = "당화혈색소 (%)",
                firstItems = Constants.RANGE_HEMOGLOBIN_A1C_FIRST_RANGE.map { it },
                firstStartIdx = Constants.RANGE_HEMOGLOBIN_A1C_FIRST_RANGE.indexOf(6),
                secondItems = Constants.RANGE_HEMOGLOBIN_A1C_SECOND_RANGE.map { it },
                secondStartIdx = Constants.RANGE_HEMOGLOBIN_A1C_SECOND_RANGE.indexOf(5),
                onSelectItemValue = {
                    viewModel.updateHemoglobinA1cNumber(it)
                }, onBack = {
                    navController.popBackStack()
                }
            )

            Spacer(modifier = Modifier.height(32.dp))

            ItemSmallInputText(onChangedText = {
                viewModel.updateMemo(it)
            })
        }

        ItemInputBottomMenu(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .align(Alignment.BottomCenter)
                .background(MaterialTheme.colorScheme.primary),
            onClick = {
                viewModel.insertHemoglobinA1c()
                navController.popBackStack()
            },
            onDismiss = {
                navController.popBackStack()
            }
        )
    }
}