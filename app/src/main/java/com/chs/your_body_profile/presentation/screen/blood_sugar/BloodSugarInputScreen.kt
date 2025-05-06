package com.chs.your_body_profile.presentation.screen.blood_sugar

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.domain.model.MeasureType
import com.chs.your_body_profile.presentation.common.ItemCurrentDateTime
import com.chs.your_body_profile.presentation.common.ItemInputBottomMenu
import com.chs.your_body_profile.presentation.common.ItemMeasureTypeHorizontalList
import com.chs.your_body_profile.presentation.common.ItemSmallInputText
import com.chs.your_body_profile.presentation.common.NumberPicker
import com.chs.your_body_profile.presentation.ui.theme.YourBodyProfileTheme
import java.time.LocalDate

@Composable
fun BloodSugarInputScreenRoot(
    viewModel: BloodSugarInputViewModel,
    onBack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    BloodSugarInputScreen(state = state) { event ->
    }
}

@Composable
fun BloodSugarInputScreen(
    state: BloodSugarInputState,
    onEvent: (BloodSugarInputEvent) -> Unit
) {
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
            ItemCurrentDateTime {
//                viewModel.updateBloodSugarMeasureTime(it)
            }

            Spacer(modifier = Modifier.height(16.dp))

            NumberPicker(
                title = "혈당 (mg/dL)",
                items = Constants.RANGE_BLOOD_SUGAR_NUMBER.map { it },
                startIdx = Constants.RANGE_BLOOD_SUGAR_NUMBER.indexOf(100),
                onBack = {  },
                onSelectItemValue = { number ->
//                viewModel.updateBloodSugarNumber(number)
                }
            )

            Spacer(modifier = Modifier.height(32.dp))

            ItemMeasureTypeHorizontalList(
                title = "현재 상태 선택",
                items = Constants.bloodSugarMeasureList
            ) { value ->
//                viewModel.updateBloodSugarMeasureType(
                    MeasureType.entries.find { it.mean.second == value } ?: MeasureType.EMPTY
//                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            ItemSmallInputText(onChangedText = {
//                viewModel.updateBloodSugarMemo(it)
            })
        }

        ItemInputBottomMenu(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .align(Alignment.BottomCenter)
                .background(MaterialTheme.colorScheme.primary),
            onClick = {
//                viewModel.insertBloodSugarInfo()
            },
            onDismiss = {
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