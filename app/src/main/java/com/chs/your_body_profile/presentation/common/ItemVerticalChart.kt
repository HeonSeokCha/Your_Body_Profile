package com.chs.your_body_profile.presentation.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.presentation.ui.theme.SkyBlue400
import java.time.LocalDate
import kotlin.math.roundToInt

@Composable
fun ItemVerticalChart(
    items: List<Pair<LocalDate, Int>>,
    onSelected: (LocalDate) -> Unit
) {
    val density = LocalDensity.current
    var selectIdx by rememberSaveable { mutableIntStateOf(0) }
    val textSize = with(density) { 10.sp.toPx() }
    val smallPadding = with(density) { 4.dp.toPx() }
    val labelSectionHeight = smallPadding.times(2) + textSize
    val textMeasurer = rememberTextMeasurer()

    Row(
        modifier = Modifier
            .height(300.dp)
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {
            repeat(3) {
                drawLine(
                    color = Color.Gray,
                    start = Offset(0f, ((100.dp.toPx() * (it) + 10))),
                    end = Offset(size.width, ((100.dp.toPx() * (it) + 10)))
                )
            }
            val scale = calculateScale(
                (size.height - smallPadding).roundToInt(),
                items.map { it.second }
            )
            val chartBottomArea = size.height - labelSectionHeight
            items.forEachIndexed { idx, pair ->
                val barHeight = pair.second.times(scale).toFloat()
                drawRoundRect(
                    color = Color.LightGray,
                    topLeft = Offset(
                        x = 6.dp.toPx() + 14.dp.toPx().times(idx) - 6.dp.toPx().div(2),
                        y = size.height - barHeight - smallPadding - labelSectionHeight
                    ),
                    size = Size(6.dp.toPx(), barHeight),
                    cornerRadius = CornerRadius(4.dp.toPx())
                )

                drawText(
                    textMeasurer = textMeasurer,
                    text = pair.first.format(Constants.DATE_DAY_FORMATTER),
                    topLeft = Offset(
                        x = 6.dp.toPx() + 14.dp.toPx().times(idx) - 6.dp.toPx() / 2,
                        y = chartBottomArea
                    )
                )
            }
        }
    }
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
fun ItemVerticalCharBar(barHeight: Float) {
    Canvas(
        modifier = Modifier
            .width(30.dp)
            .height(300.dp)
    ) {
        drawRect(
            color = SkyBlue400,
            topLeft = Offset(3.dp.toPx(), 300.dp.toPx() - barHeight),
            size = Size(24.dp.toPx(), barHeight),
        )
    }
}