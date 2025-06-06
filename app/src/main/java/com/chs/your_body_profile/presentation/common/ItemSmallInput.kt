package com.chs.your_body_profile.presentation.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.chs.your_body_profile.common.DecimalMarkedNumberVisualTransformation
import java.text.DecimalFormat

@Composable
fun ItemSmallInputWithIcon(
    icon: ImageVector,
    subComposable: @Composable () -> Unit
) {
    Card {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(32.dp)
                    .padding(start = 8.dp),
                imageVector = icon,
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(16.dp))

            subComposable()
        }
    }
}

@Composable
fun ItemSmallInputWithText(
    text: String,
    subComposable: @Composable () -> Unit
) {
    Card {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .padding(start = 16.dp),
                text = text,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.width(16.dp))

            subComposable()
        }
    }
}


@Composable
fun ItemCardIInputDecimal(
    textState: Long,
    onChangedText: (Long) -> Unit
) {
    ItemSmallInputWithText(text = "₩") {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = textState.toString(),
            singleLine = true,
            placeholder = {
                Text(
                    text = "금액",
                    color = Color.Gray
                )
            },
            colors = TextFieldDefaults.colors(
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            onValueChange = {
                it.filter { "^[0-9]".toRegex().matches(it.toString()) }
                    .ifEmpty { "0" }
                    .toLong()
                    .run {
                        onChangedText(this)
                    }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            visualTransformation = DecimalMarkedNumberVisualTransformation()
        )
    }
}

@Composable
fun ItemSmallInputText(
    textState: String?,
    onChangedText: (String) -> Unit
) {
    ItemSmallInputWithIcon(icon = Icons.Filled.Description) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = textState ?: "",
            placeholder = {
                Text(
                    text = "메모",
                    color = Color.Gray
                )
            },
            colors = TextFieldDefaults.colors(
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            onValueChange = {
                onChangedText(it)
            }
        )
    }
}

@Composable
fun ItemInputTextWithIcon(
    text: String,
    onChangedText: (String) -> Unit,
    hint: String,
    icon: ImageVector,
) {
    ItemSmallInputWithIcon(icon = icon) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            placeholder = {
                Text(
                    text = hint,
                    color = Color.Gray
                )
            },
            colors = TextFieldDefaults.colors(
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            onValueChange = {
                onChangedText(it)
            }
        )
    }
}