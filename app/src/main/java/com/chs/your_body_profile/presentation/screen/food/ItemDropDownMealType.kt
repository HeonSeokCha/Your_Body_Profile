package com.chs.your_body_profile.presentation.screen.food

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.chs.your_body_profile.domain.model.MealType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemDropDownMealType(
    initMealType: MealType,
    onSelected: (MealType) -> Unit
) {
    val list = MealType.values()
    var expandState by remember { mutableStateOf(false) }
    var selectItem by remember { mutableStateOf(initMealType) }

    ExposedDropdownMenuBox(
        expanded = expandState,
        onExpandedChange = { expandState = !expandState }
    ) {
        TextField(
            modifier = Modifier.menuAnchor(),
            readOnly = true,
            onValueChange = {},
            value = selectItem.mean.second,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandState)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )
        ExposedDropdownMenu(
            expanded = expandState,
            onDismissRequest = { expandState = false }
        ) {
            list.forEach {
                DropdownMenuItem(
                    text = { Text(text = it.mean.second) },
                    onClick = {
                        selectItem = it
                        expandState = false
                        onSelected(it)
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}