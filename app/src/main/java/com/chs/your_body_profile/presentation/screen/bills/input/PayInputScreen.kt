package com.chs.your_body_profile.presentation.screen.bills.input

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.chs.your_body_profile.presentation.common.ItemCardIInputDecimal
import com.chs.your_body_profile.presentation.common.ItemCurrentDateTime
import com.chs.your_body_profile.presentation.common.ItemInputBottomMenu
import com.chs.your_body_profile.presentation.common.ItemInputTextWithIcon
import com.chs.your_body_profile.presentation.common.ItemSmallInputText
import com.chs.your_body_profile.presentation.common.ItemSmallInputWithIcon
import com.chs.your_body_profile.presentation.common.picker.ItemDateTimePickerDialog
import com.chs.your_body_profile.presentation.screen.BaseEffect

@Composable
fun PayInputScreenRoot(
    viewModel: PayInputViewModel,
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

    PayInputScreen(state) { event ->
        when (event) {
            PayInputEvent.OnBack -> onBack()
            else -> viewModel.changeIntent(event)
        }
    }
}

@Composable
fun PayInputScreen(
    state: PayInputState,
    onEvent: (PayInputEvent) -> Unit
) {
    if (state.isShowDateTimePicker) {
        ItemDateTimePickerDialog(
            currentTime = state.paymentTime,
            onDismiss = {
                onEvent(PayInputEvent.ChangeShowDateTimePicker)
            },
            onSelectTime = {
                onEvent(PayInputEvent.ChangeDateTime(it))
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            ItemCurrentDateTime(currentDateTime = state.paymentTime) {
                onEvent(PayInputEvent.ChangeShowDateTimePicker)
            }

            Spacer(modifier = Modifier.height(16.dp))

            ItemInputTextWithIcon(
                text = state.title,
                onChangedText = {
                    onEvent(PayInputEvent.OnChangePayTitle(it))
                },
                hint = "방문한 병원",
                icon = Icons.Default.LocalHospital
            )

            Spacer(modifier = Modifier.height(16.dp))

            ItemCardIInputDecimal(
                textState = state.amount,
                onChangedText = {
                    onEvent(PayInputEvent.OnChangePayAmount(it))
                }
            )

            Spacer(modifier = Modifier.height(32.dp))

            ItemSmallInputText(
                textState = state.memo,
                onChangedText = {
                    onEvent(PayInputEvent.OnChangeMemo(it))
                }
            )
        }

        ItemInputBottomMenu(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(MaterialTheme.colorScheme.primary),
            onClick = {
                onEvent(PayInputEvent.OnClickSaveButton)
            },
            onDismiss = {
                onEvent(PayInputEvent.OnBack)
            }
        )
    }
}
