package com.chs.your_body_profile.presentation.screen.blood_sugar.input

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.chs.your_body_profile.R
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.domain.model.MealType
import com.chs.your_body_profile.domain.model.MeasureType
import com.chs.your_body_profile.presentation.common.ItemCurrentDateTime
import com.chs.your_body_profile.presentation.common.ItemExpandSingleBox
import com.chs.your_body_profile.presentation.common.ItemInputBottomMenu
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
            ItemCurrentDateTime(state.measureDateTime) {
                onEvent(BloodSugarInputEvent.ChangeShowDateTimePicker)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectableGroup(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                MeasureType.entries.forEach { info ->
                    val pair = info.mean
                    Row(
                        Modifier
                            .height(56.dp)
                            .selectable(
                                selected = info == state.selectedMeasureIdx,
                                onClick = {
                                    onEvent(BloodSugarInputEvent.OnChangeMeasureType(info))
                                },
                                role = Role.RadioButton
                            )
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = info == state.selectedMeasureIdx,
                            onClick = null
                        )

                        Text(
                            text = pair.second,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            ItemPicker(
                title = stringResource(R.string.text_input_blood_sugar),
                items = Constants.RANGE_BLOOD_SUGAR_NUMBER.map { it },
                startIdx = Constants.RANGE_BLOOD_SUGAR_NUMBER.indexOf(state.level),
                onSelectItemValue = { number ->
                    onEvent(BloodSugarInputEvent.OnChangeBloodSugarLevel(number))
                }
            )

            Spacer(modifier = Modifier.height(32.dp))

            if (state.selectedMeasureIdx == MeasureType.AFTER_EAT) {

                ItemExpandSingleBox(
                    title = "식사 시간",
                    list = MealType.entries,
                    initValue = state.selectMealTypeIdx.mean,
                    selectValue = {
                        onEvent(BloodSugarInputEvent.OnChangeMealType(it))
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                state.mealList.forEach {
                    ItemMealInfo(it) { onEvent(BloodSugarInputEvent.RemoveMealInfo(it)) }

                    Spacer(modifier = Modifier.height(4.dp))
                }

                Spacer(modifier = Modifier.height(16.dp))

                ItemInputMealInfo(
                    text = state.mealText,
                    onValueChange = {
                        onEvent(BloodSugarInputEvent.OnChangeMealName(it))
                    },
                    onAdd = {
                        onEvent(BloodSugarInputEvent.AddMealInfo)
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))
            }

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
        BloodSugarInputScreen(BloodSugarInputState(
            mealList = listOf("test", "고등어", "딸기")
        )) {

        }
    }
}