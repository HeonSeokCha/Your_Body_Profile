package com.chs.your_body_profile.presentation.screen.hemoglobinA1c

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.presentation.common.ItemCurrentDateTime
import com.chs.your_body_profile.presentation.common.ItemDualNumberPicker
import com.chs.your_body_profile.presentation.common.ItemInputBottomMenu
import com.chs.your_body_profile.presentation.common.ItemSmallInputText
import com.chs.your_body_profile.presentation.screen.blood_pressure.BloodPressureInputEvent

@Composable
fun HemoglobinA1cInputScreenRoot(
    viewModel: HemoglobinA1cInputViewModel,
    onBack: () -> Unit
) {
   val state by viewModel.state.collectAsStateWithLifecycle()
    HemoglobinA1cInputScreen(state) { event ->
        when (event) {
            HemoglobinA1icInputEvent.OnBack -> onBack()
        }
    }
}

@Composable
fun HemoglobinA1cInputScreen(
    state: HemoglobinA1cInputState,
    onEvent: (HemoglobinA1icInputEvent) -> Unit
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
            ItemCurrentDateTime(
                currentDateTime = state.measureDateTime
            ) {
            }

            Spacer(modifier = Modifier.height(16.dp))

            Spacer(modifier = Modifier.height(16.dp))

            ItemDualNumberPicker(
                title = "당화혈색소 (%)",
                firstItems = Constants.RANGE_HEMOGLOBIN_A1C_FIRST_RANGE.map { it.toString() },
                firstStartIdx = Constants.RANGE_HEMOGLOBIN_A1C_FIRST_RANGE.indexOf(6),
                secondItems = Constants.RANGE_HEMOGLOBIN_A1C_SECOND_RANGE.map { it.toString() },
                secondStartIdx = Constants.RANGE_HEMOGLOBIN_A1C_SECOND_RANGE.indexOf(5),
                onSelectItemValue = {
                }, onBack = {
                    onEvent(HemoglobinA1icInputEvent.OnBack)
                }
            )

            Spacer(modifier = Modifier.height(32.dp))

            ItemSmallInputText(onChangedText = {
//                viewModel.updateMemo(it)
            })
        }

        ItemInputBottomMenu(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .align(Alignment.BottomCenter)
                .background(MaterialTheme.colorScheme.primary),
            onClick = {
//                viewModel.insertHemoglobinA1c()
                onEvent(HemoglobinA1icInputEvent.OnBack)
            },
            onDismiss = {
                onEvent(HemoglobinA1icInputEvent.OnBack)
            }
        )
    }
}