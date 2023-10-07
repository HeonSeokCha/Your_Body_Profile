package com.chs.your_body_profile.presentation.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chs.your_body_profile.presentation.ui.theme.SkyBlue400
import android.graphics.Paint
import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import kotlin.math.roundToInt

@Composable
fun ItemVerticalChart(
    list: List<Int>,
    onSelected: (Int) -> Unit
) {
    val density = LocalDensity.current
    val height = with(density) { 300.dp.toPx() }
    val scaleValue by remember { mutableDoubleStateOf(calculateScale(height.roundToInt(), list)) }
    var selectIdx by remember { mutableIntStateOf(0) }

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
        reverseLayout = true
    ) {
        itemsIndexed(list) { idx, value ->
            Column(
                modifier = Modifier
                    .clickable {
                        selectIdx = idx
                        onSelected(value)
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ItemVerticalCharBar(barHeight = value.times(scaleValue).toFloat())

                ItemChartDate(
                    date = LocalDate.now().minusDays(idx.toLong()),
                    isFocused = selectIdx == idx
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
            "${date.month.value}/${date.dayOfMonth}"
        } else {
            if (date.dayOfMonth == 1) {
                "${date.month.value}/${date.dayOfMonth}"
            } else {
                date.dayOfMonth.toString()
            }
        },
        fontSize = 22.sp
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