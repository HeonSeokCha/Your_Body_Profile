package com.chs.your_body_profile.presentation.screen.food

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.chs.your_body_profile.common.toMillis
import com.chs.your_body_profile.presentation.Screens
import com.chs.your_body_profile.presentation.common.ItemInputButton
import com.chs.your_body_profile.presentation.common.ItemMealTypeAlertDialog
import com.chs.your_body_profile.presentation.common.ItemVerticalChart
import com.chs.your_body_profile.presentation.common.toDecimalPlace

@OptIn(ExperimentalLayoutApi::class, ExperimentalFoundationApi::class)
@Composable
fun MealListScreen(
    navController: NavHostController,
    viewModel: MealListViewModel = hiltViewModel()
) {

    val context: Context = LocalContext.current
    val state by viewModel.state.collectAsStateWithLifecycle()
    val pagingItems = state.chartList?.collectAsLazyPagingItems()
    val removeMealInfoList = remember { mutableStateListOf<TakenMealInfo>() }
    var isEditMode: Boolean by remember { mutableStateOf(false) }
    var isShowMealTypeDialog by remember { mutableStateOf(false) }

    if (isShowMealTypeDialog) {
        ItemMealTypeAlertDialog(onDisMiss = { isShowMealTypeDialog = it }) {
            isShowMealTypeDialog = false
            navController.navigate(
                    "${Screens.ScreenFoodSearch.route}/$it?takenDate=${state.selectDate.toMillis()}"
            )
        }
    }

    LaunchedEffect(context, viewModel) {
        viewModel.getPagingTotalCalories()
    }

    LaunchedEffect(state.selectDate) {
        viewModel.getDayTakenMealInfo(state.selectDate)
    }

    LaunchedEffect(removeMealInfoList.size) {
        viewModel.updateRemoveList(removeMealInfoList)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            LazyColumn (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 56.dp)
            ) {
                item {
                    ItemVerticalChart(pagingItems) {
                        viewModel.updateSelectDate(it)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "${
                            state.dayTakenMealList.map { it.value.map { it.calorie }.sum() }.sum().toInt()
                        } kcal",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                }

                items(state.dayTakenMealList.size) {
                    val key = state.dayTakenMealList.keys.toList()[it]
                    val values = state.dayTakenMealList.values.toList()[it]
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .combinedClickable(
                                onLongClick = {
                                    isEditMode = true
                                },
                                onClick = {
                                    if (isEditMode) {
                                        if (removeMealInfoList.contains(key)) {
                                            removeMealInfoList.remove(key)
                                        } else {
                                            removeMealInfoList.add(key)
                                        }
                                    } else {
                                        navController.navigate(
                                            "${Screens.ScreenMealHistoryInput.route}" +
                                                    "/${key.takenDate.toMillis()}" +
                                                    "/${key.mealType.mean.second}"
                                        )
                                    }
                                }
                            )
                    ) {
                        Column(
                            modifier = Modifier
                                .size(55.dp)
                                .clip(CircleShape)
                                .background(Color.LightGray),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(text = "${values.map { it.calorie }.sum().toInt()}")
                            Text(
                                text = "kcal",
                                fontSize = 12.sp
                            )
                        }

                        Column(
                            modifier = Modifier
                                .padding(start = 18.dp)
                        ) {
                            Text(
                                text = key.mealType.mean.second,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold
                            )
                            FlowRow {
                                values.forEachIndexed { index, foodDetailInfo ->
                                    Text(
                                        text = foodDetailInfo.name,
                                        color = Color.Gray
                                    )
                                    if (index != values.size -1) {
                                        Text(
                                            text = ", ",
                                            color = Color.Gray
                                        )
                                    }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                }

                if (state.dayTakenMealList.isNotEmpty()) {
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "탄수화물 : (${
                                    state.dayTakenMealList.map {
                                        it.value.map { it.carbohydrate }.sum()
                                    }
                                        .sum()
                                        .toDecimalPlace()
                                }) g"
                            )
                            Text(
                                text = "지방 : (${
                                    state.dayTakenMealList.map { it.value.map { it.fat }.sum() }
                                        .sum()
                                        .toDecimalPlace()
                                }) g"
                            )
                            Text(
                                text = "단백질 : (${
                                    state.dayTakenMealList.map { it.value.map { it.protein }.sum() }
                                        .sum()
                                        .toDecimalPlace()
                                }) g"
                            )
                        }
                    }
                }
            }
        }

        ItemInputButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .align(Alignment.BottomCenter)
                .background(MaterialTheme.colorScheme.primary),
        ) {
            isShowMealTypeDialog = true
        }
    }
}