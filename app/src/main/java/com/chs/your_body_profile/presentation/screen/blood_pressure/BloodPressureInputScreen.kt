package com.chs.your_body_profile.presentation.screen.blood_pressure

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.chs.your_body_profile.R
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.presentation.common.ItemCurrentDateTime
import com.chs.your_body_profile.presentation.common.ItemDateTimePicker
import com.chs.your_body_profile.presentation.common.ItemInputBottomMenu
import com.chs.your_body_profile.presentation.common.ItemPicker
import com.chs.your_body_profile.presentation.common.ItemSmallInputText

@Composable
fun BloodPressureInputScreenRoot(
    viewModel: BloodPressureInputViewModel,
    onBack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    BloodPressureInputScreen(state = state) { event ->
        when (event) {
            BloodPressureInputEvent.OnBack -> { onBack() }
            else -> viewModel.changeEvent(event)
        }
    }
}

@Composable
fun BloodPressureInputScreen(
    state: BloodPressureInputState,
    onEvent: (BloodPressureInputEvent) -> Unit
) {

    if (state.isShowDateTimePicker) {
        Dialog(
            onDismissRequest = { onEvent(BloodPressureInputEvent.ChangeShowDateTimePicker) },
            properties = DialogProperties(decorFitsSystemWindows = false)
        ) {
            ItemDateTimePicker(
                title = "",
                currentTime = state.measureDateTime
            ) {
                onEvent(BloodPressureInputEvent.ChangeDateTime(it))
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
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
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ItemCurrentDateTime(
                currentDateTime = state.measureDateTime
            ) {
                onEvent(BloodPressureInputEvent.ChangeShowDateTimePicker)
            }

            Spacer(modifier = Modifier.height(16.dp))

            ItemPicker(
                title = stringResource(id = R.string.text_input_blood_pressure_systolic),
                items = Constants.RANGE_BLOOD_PRESSURE_SYSTOLIC_NUMBER.map { it.toString() },
                startIdx = Constants.RANGE_BLOOD_PRESSURE_SYSTOLIC_NUMBER.indexOf(90),
                onBack = {
                    onEvent(BloodPressureInputEvent.OnBack)
                },
                onSelectItemValue = { number ->
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            ItemPicker(
                title = stringResource(id = R.string.text_input_blood_pressure_diastolic),
                items = Constants.RANGE_BLOOD_PRESSURE_DIASTOLIC_NUMBER.map { it.toString() },
                startIdx = Constants.RANGE_BLOOD_PRESSURE_DIASTOLIC_NUMBER.indexOf(120),
                onBack = {
                    onEvent(BloodPressureInputEvent.OnBack)
                },
                onSelectItemValue = { number ->
                }
            )

            Spacer(modifier = Modifier.height(32.dp))

            ItemSmallInputText(onChangedText = {})
        }

        ItemInputBottomMenu(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .align(Alignment.BottomCenter)
                .background(MaterialTheme.colorScheme.primary),
            onClick = {

            },
            onDismiss = {
                onEvent(BloodPressureInputEvent.OnBack)
            }
        )
    }
}

