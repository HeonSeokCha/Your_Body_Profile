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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.presentation.common.ItemBottomMenu
import com.chs.your_body_profile.presentation.common.ItemCurrentDateTime
import com.chs.your_body_profile.presentation.common.ItemMeasureTypeList
import com.chs.your_body_profile.presentation.common.ItemSmallInputText
import com.chs.your_body_profile.presentation.common.NumberPicker

@Composable
fun InputBloodSugarScreen(
    navController: NavHostController,
    viewModel: BloodSugarInputViewModel = hiltViewModel()
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
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ItemCurrentDateTime {

            }
            Spacer(modifier = Modifier.height(16.dp))

            NumberPicker(
                title = "혈당 (mg/dL)",
                items = Constants.RANGE_BLOOD_SUGAR_NUMBER.map { it.toString() }
            ) { number ->

            }

            Spacer(modifier = Modifier.height(32.dp))

            ItemMeasureTypeList(
                title = "현재 상태 선택",
                items = Constants.BLOOD_SUGAR_MEASURE_TYPE_LIST
            ) {

            }

            Spacer(modifier = Modifier.height(16.dp))

            ItemSmallInputText(onChangedText = { })

        }

        ItemBottomMenu(
            modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .align(Alignment.BottomCenter)
            .background(MaterialTheme.colorScheme.primary),
            onClick = {

            },
            onDismiss = {
                navController.popBackStack()
            }
        )
    }
}


