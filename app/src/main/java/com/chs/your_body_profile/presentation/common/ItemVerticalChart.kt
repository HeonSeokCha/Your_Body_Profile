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
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun ItemVerticalChart(
    list: List<Int>,
    onSelected: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .height(300.dp)
            .horizontalScroll(rememberScrollState())
    ) {
        val density = LocalDensity.current
        val skyBlue = SkyBlue400
        val horizontalPadding = with(density) { 12.dp.toPx() }
        val distance = with(density) { 26.dp.toPx() }
        val calculatedWidth = with(density) {
            (distance.times(list.size) + horizontalPadding.times(2)).toDp()
        }
        val barWidth = with(density) { 12.dp.toPx() }
        val selectionWidth = with(density) { 20.dp.toPx() }
        val smallPadding = with(density) { 4.dp.toPx() }
        val textSize = with(density) { 22.sp.toPx() }
        val cornerRadius = with(density) { 4.dp.toPx() }
        val labelSelectionHeight = smallPadding.times(2) + textSize
        val paint = Paint().apply {
            color = 0xffff47586B.toInt()
            textAlign = Paint.Align.CENTER
            this.textSize = textSize
        }

        val barAreas = list.mapIndexed { index, i ->
            BarArea(
                index = index,
                value = i,
                xStart = horizontalPadding + distance.times(index) - distance.div(2),
                xEnd = horizontalPadding + distance.times(index) + distance.div(2)
            )
        }

        var selectedPos by remember { mutableFloatStateOf(barAreas.first().xStart.plus(1f)) }
        var tempPos by remember { mutableFloatStateOf(-1000f) }
        val selectBar by remember(selectedPos, barAreas) {
            derivedStateOf {
                barAreas.find { it.xStart < selectedPos && selectedPos < it.xEnd }
            }
        }

        val tempBar by remember(tempPos, barAreas) {
            derivedStateOf {
                barAreas.find { it.xStart < tempPos && tempPos < it.xEnd }
            }
        }

        val scope = rememberCoroutineScope()
        val animateAble = remember { androidx.compose.animation.core.Animatable(1f) }
        val tempAnimateAble = remember { androidx.compose.animation.core.Animatable(0f) }
        val textMeasure = rememberTextMeasurer()

        Canvas(
            modifier = Modifier
                .fillMaxHeight()
                .width(calculatedWidth)
                .tapOrPress(
                    onStart = { pos ->
                        scope.launch {
                            selectBar?.let { selected ->
                                if (pos in selected.xStart..selected.xEnd) {

                                } else {
                                    tempPos = pos
                                    scope.launch {
                                        tempAnimateAble.snapTo(0f)
                                        tempAnimateAble.animateTo(1f, animationSpec = tween(300))
                                    }
                                }
                            }
                        }
                    }, onCancel = { pos ->
                        tempPos = -Int.MAX_VALUE.toFloat()
                        scope.launch {
                            tempAnimateAble.animateTo(0f)
                        }
                    }, onCompleted = {
                        val currentSelected = selectBar
                        scope.launch {
                            selectedPos = it
                            animateAble.snapTo(tempAnimateAble.value)
                            selectBar?.value?.let { value ->
                                onSelected(value)
                            }
                            async {
                                animateAble.animateTo(
                                    targetValue = 1f,
                                    animationSpec = tween(
                                        300
                                            .times(1f - tempAnimateAble.value)
                                            .roundToInt()
                                    )
                                )
                            }
                            async {
                                tempAnimateAble.snapTo(0f)
                                currentSelected?.let {
                                    tempPos = currentSelected.xStart.plus(1f)
                                    tempAnimateAble.snapTo(1f)
                                    tempAnimateAble.animateTo(0f, tween(300))
                                }
                            }
                        }
                    }
                )
        ) {
           val lineDistance = size.height.minus(smallPadding.times(2)).div(4)
            repeat(5) {
                drawLine(
                    color = Color.Gray,
                    start = Offset(0f, smallPadding.plus(it.times(lineDistance))),
                    end = Offset(size.width, smallPadding.plus(it.times(lineDistance)))
                )
            }

            val scale = calculateScale((size.height - smallPadding).roundToInt(), list)

            val chartAreaBottom = size.height - labelSelectionHeight
            barAreas.forEachIndexed { idx, item ->
                val barHeight = item.value.times(scale).toFloat()
                drawRoundRect(
                    color = skyBlue,
                    topLeft = Offset(
                        x = horizontalPadding + distance.times(idx) - barWidth.div(2),
                        y = size.height - barHeight - smallPadding
                    ),
                    size = Size(barWidth, barHeight),
                    cornerRadius = CornerRadius(cornerRadius)
                )

                val textPosX = horizontalPadding + distance.times(idx) - barWidth / 2
                val textPosY = chartAreaBottom - barHeight - smallPadding - 10f
                drawText(
                    textMeasurer = textMeasure,
                    text = "${item.value}",
                    topLeft = Offset(textPosX, textPosY)
                )
            }

            if (selectBar != null) {
                drawRoundRect(
                    brush = Brush.verticalGradient(
                        listOf(
                            skyBlue.copy(alpha = 0.3f),
                            Color.Transparent
                        )
                    ),
                    topLeft = Offset(
                        x = horizontalPadding + distance.times(selectBar!!.index) - selectionWidth.div(2),
                        y = size.height + smallPadding - size.height.times(animateAble.value)
                    ),
                    size = Size(selectionWidth, size.height.minus(smallPadding.times(2))),
                    cornerRadius = CornerRadius(cornerRadius)
                )
            }

            if (tempBar != null) {
                drawRoundRect(
                    brush = Brush.verticalGradient(
                        listOf(
                            skyBlue.copy(alpha = 0.3f),
                            Color.Transparent
                        )
                    ),
                    topLeft = Offset(
                        x = horizontalPadding + distance.times(tempBar!!.index) - selectionWidth.div(
                            2
                        ), y = size.height + smallPadding - size.height.times(tempAnimateAble.value)
                    ),
                    size = Size(selectionWidth, size.height.minus(smallPadding.times(2))),
                    cornerRadius = CornerRadius(cornerRadius)
                )
            }
        }
    }
}

data class BarArea(
    val index: Int,
    val xStart: Float,
    val xEnd: Float,
    val value: Int
)