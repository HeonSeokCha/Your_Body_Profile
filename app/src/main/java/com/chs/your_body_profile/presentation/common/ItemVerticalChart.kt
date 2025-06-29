package com.chs.your_body_profile.presentation.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.presentation.ui.theme.SkyBlue400
import java.time.LocalDate
import kotlin.math.roundToInt

@Composable
fun <T> ItemVerticalChart(
    pagingItems: LazyPagingItems<Pair<LocalDate, List<T>>>,
    selectIdx: Int,
    onSelected: (Int) -> Unit
) {
    val scrollState = rememberLazyListState()

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .drawBehind {
                repeat(3) {
                    drawLine(
                        color = Color.Gray,
                        start = Offset(0f, ((100.dp.toPx() * (it) + 10))),
                        end = Offset(size.width, ((100.dp.toPx() * (it) + 10)))
                    )
                }
            },
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        reverseLayout = true,
        state = scrollState
    ) {
        items(pagingItems.itemCount) { idx ->
            val date = pagingItems[idx]!!.first

            Column(
                modifier = Modifier
                    .drawBehind {
                        if (selectIdx == idx) {
                            drawRoundRect(
                                brush = Brush.verticalGradient(
                                    listOf(
                                        SkyBlue400.copy(alpha = 0.3f),
                                        Color.Transparent
                                    )
                                ),
                                topLeft = Offset(0f, 0f),
                                size = Size(size.width, size.height)
                            )
                        }
                    }
                    .clickable {
                        onSelected(idx)
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ItemVerticalCharBar()

                ItemChartDate(
                    date = date,
                    isFocused = selectIdx == idx
                )
            }
        }
    }
}

@Composable
fun ItemSingleValue(
    pagingItems: LazyPagingItems<Pair<LocalDate, Int>>?,
    onSelected: (LocalDate) -> Unit
) {

}

@Composable
fun ItemMultipleValue(
    pagingItems: LazyPagingItems<Pair<LocalDate, Pair<Int, Int>>>?,
    onSelected: (LocalDate) -> Unit
) {
    
}

@Composable
fun ItemChartDate(
    date: LocalDate,
    isFocused: Boolean = false
) {
    Text(
        text = if (isFocused) {
            date.format(Constants.DATE_MONTH_DAY_FORMATTER)
        } else {
            if (date.dayOfMonth == 1) {
                date.format(Constants.DATE_MONTH_DAY_FORMATTER)
            } else {
                date.format(Constants.DATE_DAY_FORMATTER)
            }
        },
        fontSize = 20.sp
    )
}

@Composable
fun ItemVerticalCharBar() {
    Canvas(
        modifier = Modifier
            .width(30.dp)
            .height(150.dp)
    ) {
        drawRect(
            color = SkyBlue400,
            topLeft = Offset(3.dp.toPx(), 150.dp.toPx() - 25.dp.toPx()),
            size = Size(24.dp.toPx(), 25.dp.toPx()),
        )
    }
}
