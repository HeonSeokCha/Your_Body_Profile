package com.chs.your_body_profile.presentation.common.chart

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chs.your_body_profile.common.calculateScale
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun ColumnChart(

) {

}

@Composable
fun ItemDailyChart(
    hourUsageList: List<Pair<Int, Int>>,
    clickText: DrawScope.(
        TextMeasurer,
        BarArea,
    ) -> Unit
) {
    val density = LocalDensity.current
    val textSize = with(density) { 10.sp.toPx() }
    val smallPadding = with(density) { 4.dp.toPx() }
    val labelSectionHeight = smallPadding.times(2) + textSize
    val topBasePadding = with(density) { 14.sp.toPx() + 21f }
    val barWidth = with(density) { 8.dp.toPx() }
    val distance = with(density) {
        (LocalConfiguration.current.screenWidthDp - 25).div(24).dp.toPx()
    }
    val barColor = MaterialTheme.colorScheme.primary

    val textMeasurer = rememberTextMeasurer()

    val style1 = TextStyle(
        fontSize = 10.sp,
        color = Color.Black
    )

    val basePadding = textMeasurer
        .measure(
            text = "",
            style = style1
        ).size
        .width
        .div(2)
        .toFloat()

    val barAreas = hourUsageList.mapIndexed { idx, pair ->
        BarArea(
            idx = idx,
            value = pair.second,
            xStart = basePadding + smallPadding + distance.times(idx) - barWidth,
            xEnd = basePadding + smallPadding + distance.times(idx) + barWidth
        )
    }

    var selectedBar: BarArea? by remember { mutableStateOf(null) }
    var selectedPos by remember { mutableFloatStateOf(0f) }


    LaunchedEffect(barAreas) {
        selectedBar = null
    }

    LaunchedEffect(selectedPos) {
        val findBar = barAreas.find { selectedPos in it.xStart..it.xEnd }
        selectedBar = if (findBar?.value == 0) {
            null
        } else findBar
    }

    val scope = rememberCoroutineScope()

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(horizontal = 4.dp)
            .tapOrPress {
                scope.launch { selectedPos = it }
            }
    ) {
        if (size.height != 0f) {
            repeat(3) {
                drawLine(
                    color = Color.Gray,
                    start = Offset(basePadding.div(2), ((size.height / 3) * it) + topBasePadding),
                    end = Offset(
                        size.width - basePadding.div(2),
                        ((size.height / 3) * it) + topBasePadding
                    )
                )
            }
        }
        val scale = calculateScale(
            (size.height - smallPadding - topBasePadding).roundToInt(),
            hourUsageList.map { it.second }
        )
        val chartAreaBottom = size.height - labelSectionHeight

         barAreas.forEachIndexed { idx, info ->
            val barHeight = info.value.times(scale).toFloat()
            drawRoundRect(
                color = barColor,
                topLeft = Offset(
                    x = basePadding + smallPadding + (distance).times(idx) - barWidth,
                    y = size.height - barHeight - smallPadding - labelSectionHeight
                ),
                size = Size(barWidth, barHeight),
                cornerRadius = CornerRadius(4.dp.toPx())
            )

            if (info.idx % 6 == 0 || info.idx == 23) {
                val time = if (info.idx == 23) "(시)" else info.idx.toString()

                val textResult = textMeasurer.measure(
                    text = time,
                    style = style1
                )

                val textRectPadding = when (time) {
                    "0" -> 42f
                    "(시)" -> {
                        size.width - (textResult.size.width + 42f)
                    }

                    else -> {
                        size.width.div(4).times(time.toInt() / 6) - textResult.size.width.div(2)
                    }
                }

                drawText(
                    textMeasurer = textMeasurer,
                    text = time,
                    topLeft = Offset(
                        x = textRectPadding,
                        y = chartAreaBottom
                    ),
                    style = style1
                )
            }
        }

        if (selectedBar != null) {
            val barHeight = (size.height - selectedBar!!.value.times(scale)
                .toFloat() - smallPadding - labelSectionHeight)

            drawLine(
                color = Color.Black,
                start = Offset(selectedBar!!.xStart + barWidth.div(2), 50f),
                end = Offset(selectedBar!!.xStart + barWidth.div(2), barHeight),
                strokeWidth = 4f
            )

            clickText(
                textMeasurer,
                selectedBar!!,
            )
        }
    }
}

fun Modifier.tapOrPress(
    onCompleted: (offsetX: Float) -> Unit
): Modifier = composed {
    val interactionSource = remember { MutableInteractionSource() }
    this.pointerInput(interactionSource) {
        awaitEachGesture {
            val tap = awaitFirstDown()
                .also { if (it.pressed != it.previousPressed) it.consume() }
            val up = waitForUpOrCancellation()
            if (up != null) {
                if (up.pressed != up.previousPressed) up.consume()
                onCompleted(tap.position.x)
            }
        }
    }
}