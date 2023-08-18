package com.chs.your_body_profile.presentation.screen.body_dash_board

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chs.your_body_profile.R
import com.chs.your_body_profile.presentation.ui.theme.Your_Body_ProfileTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashBoardSmallCard(
    title: String,
    value: String,
    measureType: String? = null,
    subValue: String? = null,
    onClick: () -> Unit,
    subComposable: @Composable () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.size_dash_board_height)),
        onClick = onClick,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = 8.dp,
                    start = 16.dp,
                    end = 16.dp
                ),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(text = title)

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = value,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.width(4.dp))

                if (measureType != null) {
                    Text(text = measureType)
                }
            }

            if (subValue != null) {
                Text(text = subValue)
            }

            subComposable()
        }
    }
}

@Composable
fun DrinkInfoDashBoard(
    title: String,
    value: Int,
    drinkEventClick: (Int) -> Unit,
    cardClick: () -> Unit,
) {
    DashBoardSmallCard(
        title = title,
        value = value.toString(),
        measureType = stringResource(id = R.string.text_drink_unit),
        onClick = cardClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = if (value == 0) Color.Gray else Color.Black,
                        shape = CircleShape
                    ),
                enabled = value != 0,
                onClick = { drinkEventClick(value.minus(1)) }
            ) {
                Icon(
                    Icons.Default.Remove,
                    contentDescription = null,
                )
            }

            IconButton(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = CircleShape
                    ),
                onClick = { drinkEventClick(value.plus(1)) }
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = null,
                )
            }
        }
    }
}

@Composable
fun MedicineInfoDashBoard(
    title: String,
    value: String,
    subValue: String,
    subValue2: String? = null,
    onClick: () -> Unit
) {
    DashBoardSmallCard(
        title = title,
        value = value,
        subValue = subValue,
        onClick = { onClick() }
    ) {
        if (subValue2 != null) {
            Text(text = subValue2)
        }
    }
}

@Composable
fun FoodInfoDashBoard(
    title: String,
    value: String,
    subValue: String,
    subValue2: String,
    onClick: () -> Unit
) {
    DashBoardSmallCard(
        title = title,
        value = value,
        subValue = subValue,
        onClick = { onClick() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = subValue2,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(text = stringResource(id = R.string.text_food_unit))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashBoardSingleLineCard(
    title: String,
    infoValue: String,
    infoUnit: String,
    subComposable: @Composable () -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.6f)
            .height(dimensionResource(id = R.dimen.size_dash_board_height)),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 16.dp,
                    end = 16.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = infoValue,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = infoUnit,
                        fontSize = 16.sp
                    )
                }
            }
            subComposable()
        }
    }
}

@Composable
fun DashBoardInputCard(
    title: String,
    infoValue: String,
    infoUnit: String,
    btnClick: () -> Unit,
    onClick: () -> Unit
) {
    DashBoardSingleLineCard(
        title = title,
        infoValue = infoValue,
        infoUnit = infoUnit,
        subComposable = {
            Button(onClick = btnClick) {
                Text("입력")
            }
        },
        onClick = onClick
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewHomeBasicInfoCard() {
    Your_Body_ProfileTheme {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            contentPadding = PaddingValues(
                start = 8.dp,
                end = 8.dp,
                bottom = 100.dp
            ),
            modifier = Modifier.fillMaxSize(),
            verticalItemSpacing = 4.dp,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            item {
                MedicineInfoDashBoard(
                    title = stringResource(id = R.string.text_title_take_medicine),
                    value = "아침",
                    subValue = "당뇨약",
                    subValue2 = "오전 07시 41분"
                ) {

                }
            }
            item {
                DrinkInfoDashBoard(
                    title = stringResource(id = R.string.text_title_drink_water),
                    value = 1,
                    drinkEventClick = {},
                    cardClick = {

                    }
                )
            }

            item {
                FoodInfoDashBoard(
                    title = stringResource(id = R.string.text_title_food),
                    value = "아침",
                    subValue = "삶은 계란",
                    subValue2 = "400"
                ) {

                }
            }

            item {
                DrinkInfoDashBoard(
                    title = stringResource(id = R.string.text_title_drink_coffee),
                    value = 0,
                    drinkEventClick = { }
                    , cardClick = {

                    }
                )
            }
            item(span = StaggeredGridItemSpan.FullLine) {
                DashBoardInputCard(
                    title = "혈당",
                    infoValue = "--",
                    infoUnit = "mg/dL",
                    onClick = {},
                    btnClick = {}
                )
            }
            item(span = StaggeredGridItemSpan.FullLine) {
                DashBoardInputCard(
                    title = "혈당",
                    infoValue = "--",
                    infoUnit = "mg/dL",
                    onClick = {},
                    btnClick = {}
                )
            }
        }
    }
}

