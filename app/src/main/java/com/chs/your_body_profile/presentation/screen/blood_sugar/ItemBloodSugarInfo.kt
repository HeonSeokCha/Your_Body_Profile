package com.chs.your_body_profile.presentation.screen.blood_sugar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NoMeals
import androidx.compose.material.icons.filled.RamenDining
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chs.your_body_profile.R
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.domain.model.BloodSugarInfo
import com.chs.your_body_profile.domain.model.MeasureType
import com.chs.your_body_profile.presentation.common.ItemDetailInfo
import com.chs.your_body_profile.presentation.common.ItemSimpleInfo

@Composable
fun ItemSimpleBloodSugarInfo(
    bloodSugarInfo: BloodSugarInfo,
    onClick: (BloodSugarInfo) -> Unit,
    onLongClick: (BloodSugarInfo) -> Unit
) {
    ItemSimpleInfo(
        title = bloodSugarInfo.number.toString(),
        measureUnit = stringResource(id = R.string.text_blood_sugar_unit),
        subTitle = bloodSugarInfo.measureDateTime.format(Constants.DATE_TIME_FORMATTER),
        onClick = { onClick(bloodSugarInfo) },
        onLongClick = { onLongClick(bloodSugarInfo) }
    ) {
        Row {
            Text(text = bloodSugarInfo.measureTypeIdx.mean.second)
            if (bloodSugarInfo.measureTypeIdx == MeasureType.BEFORE_EAT) {
                Icon(imageVector = Icons.Default.NoMeals, contentDescription = null)
            } else {
                Icon(imageVector = Icons.Default.RamenDining, contentDescription = null)
            }
        }
    }
}

@Composable
fun ItemDetailBloodSugarInfo(
    bloodSugarInfo: BloodSugarInfo,
    onDismiss: () -> Unit
) {
    ItemDetailInfo(
        dateTime = bloodSugarInfo.measureDateTime,
        subComposable = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = bloodSugarInfo.number.toString(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Text(
                            text = stringResource(R.string.text_blood_sugar_unit),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 22.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    Icon(
                        modifier = Modifier.size(32.dp),
                        imageVector =  if (bloodSugarInfo.measureTypeIdx == MeasureType.BEFORE_EAT) {
                            Icons.Default.NoMeals
                        } else {
                            Icons.Default.RamenDining
                        },
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = bloodSugarInfo.measureTypeIdx.mean.second,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 24.sp,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                }

                if (bloodSugarInfo.mealInfo.isNotEmpty()) {
                    item {
                        Text(
                            text = bloodSugarInfo.mealInfo.first().mealType.mean.second,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 24.sp,
                            color = Color.Gray
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(text = "섭취한 음식들")
                    }

                    items(bloodSugarInfo.mealInfo) {
                        Text(text = it.mealName)
                    }
                }
            }
        },
        onDismiss = onDismiss
    )
}