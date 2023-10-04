package com.chs.your_body_profile.presentation.screen.food

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.chs.your_body_profile.presentation.common.ItemVerticalChart
import kotlin.random.Random

@Composable
fun MealListScreen(
    navController: NavHostController
) {
    val list = remember {
        mutableStateOf(List(100) { Random.nextInt(0, 100) })
    }
    val state = rememberScrollState()
    Column(
        Modifier
            .fillMaxSize()
            .padding(12.dp)
            .verticalScroll(state = state)
    ) {
        val selectedItem = remember { mutableIntStateOf(list.value.first()) }
        Text(text = "Selected value: ${selectedItem.intValue}")
        ItemVerticalChart(list.value) { selectedItem.intValue = it }
    }
}