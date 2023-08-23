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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.chs.your_body_profile.R
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.presentation.common.ItemBottomMenu
import com.chs.your_body_profile.presentation.common.ItemCurrentDateTime
import com.chs.your_body_profile.presentation.common.NumberPicker

@Composable
fun InputBloodPressureScreen(
    navController: NavHostController,
    viewModel: BloodPressureInputViewModel = hiltViewModel()
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
                title = stringResource(id = R.string.text_input_blood_pressure_systolic),
                items = Constants.RANGE_BLOOD_SUGAR_NUMBER.map { it }
            ) { number ->

            }
            
            NumberPicker(
                title = stringResource(id = R.string.text_input_blood_pressure_diastolic),
                items = Constants.RANGE_BLOOD_SUGAR_NUMBER.map { it }
            ) { number ->

            }

            Spacer(modifier = Modifier.height(32.dp))
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
