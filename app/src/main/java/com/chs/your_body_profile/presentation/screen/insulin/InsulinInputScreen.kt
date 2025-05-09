package com.chs.your_body_profile.presentation.screen.insulin

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
import com.chs.your_body_profile.presentation.common.ItemInputBottomMenu
import com.chs.your_body_profile.presentation.common.picker.ItemPicker
import com.chs.your_body_profile.presentation.common.ItemSmallInputText

@Composable
fun InsulinInputScreenRoot(
    viewModel: InsulinInputViewModel,
    onBack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
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
                currentDateTime = state.injectDateTime
            ) {
                onEvent(InsulinInputEvent.ChangeShowDateTimePicker)
            }

            Spacer(modifier = Modifier.height(16.dp))

            ItemPicker(
                title = "인슐린 주입 수치",
                items = Constants.RANGE_INSULIN_NUMBER.map { it },
                startIdx = Constants.RANGE_INSULIN_NUMBER.indexOf(15),
                onSelectItemValue = { number ->
                    onEvent(InsulinInputEvent.OnChangeInsulinLevel(number!!))
                }
            )

            Spacer(modifier = Modifier.height(32.dp))

            ItemSmallInputText(
                onChangedText = {
                })
        }


        ItemInputBottomMenu(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .align(Alignment.BottomCenter)
                .background(MaterialTheme.colorScheme.primary),
            onClick = {
                onEvent(InsulinInputEvent.OnBack)
            },
            onDismiss = {
                onEvent(InsulinInputEvent.OnBack)
            }
        )
    }
}