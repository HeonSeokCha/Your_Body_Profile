package com.chs.your_body_profile.presentation.screen.food

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
import com.chs.your_body_profile.common.toMillis
import com.chs.your_body_profile.domain.model.FoodDetailInfo
import com.chs.your_body_profile.domain.model.MealType
import com.chs.your_body_profile.presentation.Screens
import com.chs.your_body_profile.presentation.common.ItemInputBottomMenu
import com.chs.your_body_profile.presentation.common.ItemTimePicker
import com.chs.your_body_profile.presentation.ui.theme.SkyBlue400
import java.time.LocalDate
import kotlin.math.roundToInt

@Composable
fun MealHistoryInputScreen(
    takenDate: LocalDate,
    takenMealType: MealType,
    foodList: List<FoodDetailInfo>,
    navController: NavHostController,
    viewModel: MealHistoryInputViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(context, viewModel) {
        viewModel.initMealHistoryInfo(
            takenDate = takenDate,
            takenMealType = takenMealType,
            foodList = foodList
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(
                top = 32.dp
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                ItemDropDownMealType(initMealType = takenMealType) {
                    viewModel.updateMealType(it)
                }
            }

            item {

                Spacer(modifier = Modifier.height(16.dp))
                ItemTimePicker(dateTime = state.takenTime) {

                }
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(
                            SkyBlue400.copy(
                                alpha = 0.4f
                            )
                        )
                ) {
                    state.takenFoodList.forEach {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp)
                        ) {

                            Text(
                                text = it.name,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Row {
                                Text(
                                    text = "${it.calorie.roundToInt()}kcal, ",
                                    color = Color.Gray
                                )

                                Text(
                                    text = "${it.servingWeight.roundToInt()}g",
                                    color = Color.Gray
                                )
                            }
                            Divider(color = Color.Gray)
                        }
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                            .clickable {
                                navController.navigate(
                                    "${Screens.ScreenFoodSearch.route}" +
                                            "/${state.mealType!!.mean.second}" +
                                            "?takenDate=${state.takenDate.toMillis()}"
                                )
                            }
                    ) {
                        Text(
                            text = "음식 추가",
                            fontSize = 22.sp
                        )
                    }
                }
            }
        }

        ItemInputBottomMenu(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .align(Alignment.BottomCenter)
                .background(MaterialTheme.colorScheme.primary),
            onClick = {
                  if (state.takenFoodList.isNotEmpty()) {
                      viewModel.insertMealHistory()
                      navController.popBackStack()
                  }
            }, onDismiss = {
                navController.popBackStack()
            }
        )
    }
}