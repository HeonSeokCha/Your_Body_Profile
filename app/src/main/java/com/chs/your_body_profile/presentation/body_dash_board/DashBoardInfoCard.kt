package com.chs.your_body_profile.presentation.body_dash_board

import androidx.compose.foundation.background
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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
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
    subValue: String,
    onClick: () -> Unit,
    subComposable: @Composable () -> Unit
) {
    Card(onClick = onClick,
    ) {

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
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

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
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                DashBoardInputCard(
                    title = "혈당",
                    infoValue = "--",
                    infoUnit = "mg/dL",
                    onClick = {},
                    btnClick = {}
                )
            }
            item {
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

