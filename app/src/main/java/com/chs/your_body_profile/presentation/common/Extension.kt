package com.chs.your_body_profile.presentation.common

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.consumeDownChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import kotlinx.coroutines.coroutineScope

fun Modifier.shimmerEffect(): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }

    val transition = rememberInfiniteTransition(label = "shimmer")
    val startOffsetX by transition.animateFloat(
        label = "shimmer",
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(1000)
        )
    )

    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFFB8B5B5),
                Color(0xFF8F8B8B),
                Color(0xFFB8B5B5),
            ),
            start = Offset(
                startOffsetX,
                0f
            ),
            end = Offset(
                startOffsetX + size.width.toFloat(),
                size.height.toFloat()
            )
        )
    ).onGloballyPositioned {
        size = it.size
    }
}

fun Modifier.tapOrPress(
    onStart: (offsetX: Float) -> Unit,
    onCancel: (offsetX: Float) -> Unit,
    onCompleted: (offsetX: Float) -> Unit
): Modifier = composed {
    val interactionSource = remember { MutableInteractionSource() }
    this.pointerInput(interactionSource) {
        forEachGesture {
            coroutineScope {
                awaitPointerEventScope {
                    val tap = awaitFirstDown().also {
                        if (it.pressed != it.previousPressed) it.consume()
                    }

                    onStart(tap.position.x)
                    val up = waitForUpOrCancellation()
                    if (up == null) {
                        onCancel(tap.position.x)
                    } else {
                        if (up.pressed != up.previousPressed) up.consume()
                        onCompleted(tap.position.x)
                    }
                }
            }
        }
    }
}

fun calculateScale(viewHeightPx: Int, values: List<Int>): Double {
    return values.maxOrNull()?.let { max ->
        viewHeightPx.times(0.8).div(max)
    } ?: 1.0
}