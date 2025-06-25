package com.chs.your_body_profile.presentation.screen.insulin.input

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
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
import com.chs.your_body_profile.presentation.screen.blood_pressure.input.BloodPressureInputEvent

@Composable
fun InsulinInputScreenRoot(
    viewModel: InsulinInputViewModel,
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

    InsulinInputScreen(state) { event ->
        when (event) {
            InsulinInputEvent.OnBack -> onBack()
            else -> viewModel.changeEvent(event)
        }
    }
}

@Composable
fun InsulinInputScreen(
    state: InsulinInputState,
    onEvent: (InsulinInputEvent) -> Unit
) {
    if (state.isShowDateTimePicker) {
        ItemDateTimePickerDialog(
            currentTime = state.injectDateTime,
            onDismiss = {
                onEvent(InsulinInputEvent.ChangeShowDateTimePicker)
            },
            onSelectTime = {
                onEvent(InsulinInputEvent.ChangeDateTime(it))
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            ItemCurrentDateTime(
                currentDateTime = state.injectDateTime
            ) {
                onEvent(InsulinInputEvent.ChangeShowDateTimePicker)
            }

            Spacer(modifier = Modifier.height(16.dp))

            ItemPicker(
                title = stringResource(R.string.text_input_insulin),
                items = Constants.RANGE_INSULIN_NUMBER.map { it },
                startIdx = Constants.RANGE_INSULIN_NUMBER.indexOf(state.level),
                onSelectItemValue = { number ->
                    onEvent(InsulinInputEvent.OnChangeInsulinLevel(number))
                }
            )

            Spacer(modifier = Modifier.height(32.dp))

            ItemSmallInputText(
                textState = state.memo,
                onChangedText = {
                    onEvent(InsulinInputEvent.OnChangeMemo(it))
                }
            )
        }


        ItemInputBottomMenu(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(MaterialTheme.colorScheme.primary),
            onClick = {
                onEvent(InsulinInputEvent.OnClickSaveButton)
            },
            onDismiss = {
                onEvent(InsulinInputEvent.OnBack)
            }
        )
    }
}