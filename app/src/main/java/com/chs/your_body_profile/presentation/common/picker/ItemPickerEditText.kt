package com.chs.your_body_profile.presentation.common.picker

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun ItemScrollEditText(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    predicate: (String) -> Boolean,
    onBack: () -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    val ctx = LocalContext.current
    val kbc = LocalSoftwareKeyboardController.current
    val focusRequester = remember {
        FocusRequester()
    }
    var isEditEnable by remember { mutableStateOf(true) }

    LaunchedEffect(true) {
        focusRequester.requestFocus()
    }

    BackHandler(enabled = isEditEnable) {
        isEditEnable = false
        kbc?.hide()
        onBack()
    }

    var buffer by remember {
        mutableStateOf(
            TextFieldValue(
                text = value,
                selection = TextRange(
                    start = 0,
                    end = 0
                )
            )
        )
    }
    val action: KeyboardActionScope.() -> Unit = {
        if (predicate(buffer.text)) {
            onValueChange(buffer.text)
        } else {
            Toast.makeText(ctx, "Invalid value entered", Toast.LENGTH_SHORT).show()
            focusRequester.freeFocus()
            onValueChange(value)
        }
        kbc?.hide()
        isEditEnable = false
        onBack()
    }

    val customTextSelectionColors = TextSelectionColors(
        handleColor = Color.Transparent,
        backgroundColor = Color.Transparent
    )

    CompositionLocalProvider(
        LocalTextSelectionColors provides customTextSelectionColors
    ) {
        BasicTextField(
            modifier = modifier
                .onFocusChanged {
                    if (it.isFocused || it.hasFocus) {
                        buffer = buffer.copy(
                            selection = TextRange(
                                start = 0,
                                end = buffer.text.length
                            )
                        )
                    }
                }
                .focusRequester(focusRequester)
                .width(IntrinsicSize.Min),
            value = buffer,
            textStyle = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            ),
            onValueChange = { buffer = it },
            maxLines = 1,
            keyboardActions = KeyboardActions(onDone = action),
            keyboardOptions = keyboardOptions
        )
    }
}