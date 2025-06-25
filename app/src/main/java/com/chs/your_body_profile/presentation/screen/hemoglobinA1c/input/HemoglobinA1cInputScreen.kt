package com.chs.your_body_profile.presentation.screen.hemoglobinA1c.input

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
import androidx.core.util.toRange
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.chs.your_body_profile.R
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.presentation.common.ItemCurrentDateTime
import com.chs.your_body_profile.presentation.common.picker.ItemDualNumberPicker
import com.chs.your_body_profile.presentation.common.ItemInputBottomMenu
import com.chs.your_body_profile.presentation.common.ItemSmallInputText
import com.chs.your_body_profile.presentation.common.picker.ItemDateTimePickerDialog
import com.chs.your_body_profile.presentation.screen.BaseEffect
import kotlin.math.roundToInt

@Composable
fun HemoglobinA1cInputScreenRoot(
    viewModel: HemoglobinA1cInputViewModel,
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

    HemoglobinA1cInputScreen(state) { intent ->
        when (intent) {
            HemoglobinA1cInputEvent.OnBack -> onBack()
            else -> viewModel.changeIntent(intent)
        }
    }
}

@Composable
fun HemoglobinA1cInputScreen(
    state: HemoglobinA1cInputState,
    onEvent: (HemoglobinA1cInputEvent) -> Unit
) {
    if (state.isShowDateTimePicker) {
        ItemDateTimePickerDialog(
            currentTime = state.measureDateTime,
            onDismiss = { onEvent(HemoglobinA1cInputEvent.ChangeShowDateTimePicker) },
            onSelectTime = { onEvent(HemoglobinA1cInputEvent.ChangeDateTime(it)) }
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
                currentDateTime = state.measureDateTime
            ) {
                onEvent(HemoglobinA1cInputEvent.ChangeShowDateTimePicker)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Spacer(modifier = Modifier.height(16.dp))

            ItemDualNumberPicker(
                title = stringResource(R.string.text_input_hemoglobin_A1c),
                firstItems = Constants.RANGE_HEMOGLOBIN_A1C_FIRST_RANGE.map { it },
                firstStartIdx = Constants.RANGE_HEMOGLOBIN_A1C_FIRST_RANGE.indexOf(state.number.toInt()),
                secondItems = Constants.RANGE_INTEGER_SECOND_RANGE.map { it },
                secondStartIdx = Constants.RANGE_INTEGER_SECOND_RANGE.indexOf(((state.number % 1) * 10).roundToInt()),
                onSelectItemValue = { first, second ->
                    onEvent(HemoglobinA1cInputEvent.OnChangeHA1cInfo(first.toFloat() + (second * 0.1f)))
                }
            )

            Spacer(modifier = Modifier.height(32.dp))

            ItemSmallInputText(
                textState = state.memo,
                onChangedText = {
                    onEvent(HemoglobinA1cInputEvent.OnChangeMemo(it))
                }
            )
        }

        ItemInputBottomMenu(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(MaterialTheme.colorScheme.primary),
            onClick = {
                onEvent(HemoglobinA1cInputEvent.OnClickSaveButton)
            },
            onDismiss = {
                onEvent(HemoglobinA1cInputEvent.OnBack)
            }
        )
    }
}