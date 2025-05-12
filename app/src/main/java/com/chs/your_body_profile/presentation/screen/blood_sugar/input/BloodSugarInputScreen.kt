package com.chs.your_body_profile.presentation.screen.blood_sugar.input

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.domain.model.MeasureType
import com.chs.your_body_profile.presentation.common.ItemCurrentDateTime
import com.chs.your_body_profile.presentation.common.ItemInputBottomMenu
import com.chs.your_body_profile.presentation.common.ItemMeasureTypeHorizontalList
import com.chs.your_body_profile.presentation.common.picker.ItemPicker
import com.chs.your_body_profile.presentation.common.ItemSmallInputText
import com.chs.your_body_profile.presentation.common.picker.ItemDateTimePickerDialog
import com.chs.your_body_profile.presentation.screen.BaseEffect
import com.chs.your_body_profile.presentation.ui.theme.YourBodyProfileTheme

@Composable
fun BloodSugarInputScreenRoot(
    viewModel: BloodSugarInputViewModel,
    onBack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val effect by viewModel.effect.collectAsStateWithLifecycle(BaseEffect.Idle)

    when (effect) {
        BaseEffect.Idle -> Unit
        BaseEffect.OnBack -> { onBack() }
        is BaseEffect.ShowToast -> Unit
    }

    BloodSugarInputScreen(state = state) { intent ->
        when (intent) {
            BloodSugarInputEvent.OnBack -> onBack()
            else -> viewModel.changeIntent(intent)
        }
    }
}

@Composable
fun BloodSugarInputScreen(
    state: BloodSugarInputState,
    onEvent: (BloodSugarInputEvent) -> Unit
) {
    if (state.isShowDateTimePicker) {
        ItemDateTimePickerDialog(
            currentTime = state.measureDateTime,
            onDismiss = { onEvent(BloodSugarInputEvent.ChangeShowDateTimePicker) },
            onSelectTime = { onEvent(BloodSugarInputEvent.ChangeDateTime(it))}
        )
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ItemCurrentDateTime(state.measureDateTime) {
                onEvent(BloodSugarInputEvent.ChangeShowDateTimePicker)
            }

            Spacer(modifier = Modifier.height(16.dp))

            ItemPicker(
                title = "혈당 (mg/dL)",
                items = Constants.RANGE_BLOOD_SUGAR_NUMBER.map { it },
                startIdx = Constants.RANGE_BLOOD_SUGAR_NUMBER.indexOf(100),
                onSelectItemValue = { number ->
                    onEvent(BloodSugarInputEvent.OnChangeBloodSugarLevel(number))
                }
            )

            Spacer(modifier = Modifier.height(32.dp))

            ItemMeasureTypeHorizontalList(
                title = "현재 상태 선택",
                selectedIdx = state.selectedMeasureIdx,
                items = Constants.bloodSugarMeasureList
            ) { value ->
                onEvent(BloodSugarInputEvent.OnChangeMeasureType(value))
            }

            Spacer(modifier = Modifier.height(16.dp))

            ItemSmallInputText(
                textState = state.memo,
                onChangedText = {
                    onEvent(BloodSugarInputEvent.OnChangeMemo(it))
            })
        }

        ItemInputBottomMenu(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .align(Alignment.BottomCenter)
                .background(MaterialTheme.colorScheme.primary),
            onClick = {
                onEvent(BloodSugarInputEvent.OnClickSaveButton)
            },
            onDismiss = {
                onEvent(BloodSugarInputEvent.OnBack)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBloodSugarInputScreen() {
    YourBodyProfileTheme {
        BloodSugarInputScreen(BloodSugarInputState()) {

        }
    }
}