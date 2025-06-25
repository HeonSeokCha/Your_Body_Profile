package com.chs.your_body_profile.presentation.screen.blood_pressure.input

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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.chs.your_body_profile.R
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.presentation.common.ItemCurrentDateTime
import com.chs.your_body_profile.presentation.common.ItemInputBottomMenu
import com.chs.your_body_profile.presentation.common.picker.ItemPicker
import com.chs.your_body_profile.presentation.common.ItemSmallInputText
import com.chs.your_body_profile.presentation.common.picker.ItemDateTimePickerDialog
import com.chs.your_body_profile.presentation.screen.BaseEffect

@Composable
fun BloodPressureInputScreenRoot(
    viewModel: BloodPressureInputViewModel,
    onBack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val effect by viewModel.effect.collectAsStateWithLifecycle(BaseEffect.Idle)

    when (effect) {
        BaseEffect.Idle -> Unit
        BaseEffect.OnBack -> {
            onBack()
        }

        is BaseEffect.ShowToast -> Unit
    }

    BloodPressureInputScreen(state = state) { intent ->
        when (intent) {
            BloodPressureInputEvent.OnBack -> {
                onBack()
            }

            else -> viewModel.changeIntent(intent)
        }
    }
}

@Composable
fun BloodPressureInputScreen(
    state: BloodPressureInputState,
    onEvent: (BloodPressureInputEvent) -> Unit
) {
    if (state.isShowDateTimePicker) {
        ItemDateTimePickerDialog(
            currentTime = state.measureDateTime,
            onDismiss = {
                onEvent(BloodPressureInputEvent.ChangeShowDateTimePicker)
            },
            onSelectTime = {
                onEvent(BloodPressureInputEvent.ChangeDateTime(it))
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
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
                items = Constants.RANGE_BLOOD_PRESSURE_SYSTOLIC_NUMBER.map { it },
                startIdx = Constants.RANGE_BLOOD_PRESSURE_SYSTOLIC_NUMBER.indexOf(state.systolic),
                onSelectItemValue = { number ->
                    onEvent(BloodPressureInputEvent.OnChangeSystolic(number))
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            ItemPicker(
                title = stringResource(id = R.string.text_input_blood_pressure_diastolic),
                items = Constants.RANGE_BLOOD_PRESSURE_DIASTOLIC_NUMBER.map { it },
                startIdx = Constants.RANGE_BLOOD_PRESSURE_DIASTOLIC_NUMBER.indexOf(state.diastolic),
                onSelectItemValue = { number ->
                    onEvent(BloodPressureInputEvent.OnChangeDiastolic(number))
                }
            )

            Spacer(modifier = Modifier.height(32.dp))

            ItemSmallInputText(
                textState = state.memo,
                onChangedText = {
                    onEvent(BloodPressureInputEvent.OnChangeMemo(it))
                }
            )
        }

        ItemInputBottomMenu(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(MaterialTheme.colorScheme.primary),
            onClick = {
                onEvent(BloodPressureInputEvent.OnClickSaveButton)
            },
            onDismiss = {
                onEvent(BloodPressureInputEvent.OnBack)
            }
        )
    }
}

