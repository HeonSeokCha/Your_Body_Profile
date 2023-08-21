package com.chs.your_body_profile.presentation.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.unit.dp

@Composable
fun ItemSmallInput(
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemSmallInputText(
    onChangedText: (String) -> Unit
) {
    var textState by remember { mutableStateOf("") }
    ItemSmallInput(icon = Icons.Filled.Description) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = textState,
            placeholder = {
                Text(
                    text = "메모",
                    color = Color.Gray
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            onValueChange = { textState = it }
        )
    }
}