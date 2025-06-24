package com.chs.your_body_profile.presentation.screen.blood_sugar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chs.your_body_profile.R
import com.chs.your_body_profile.common.Constants
import com.chs.your_body_profile.domain.model.BloodSugarInfo
import com.chs.your_body_profile.domain.model.MeasureType
import com.chs.your_body_profile.presentation.ui.theme.YourBodyProfileTheme
import java.time.LocalDateTime

@Composable
fun ItemBloodSugarSummaryInfo(info: List<BloodSugarInfo>) {
    Card {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (info.count() == 1) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = info.first().number.toString(),
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

//                Image(
//                    modifier = Modifier.size(32.dp),
//                    imageVector = Constants.bloodSugarMeasureList[info.first().measureTypeIdx].second,
//                    colorFilter = ColorFilter.tint(Color.Gray),
//                    contentDescription = null
//                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = MeasureType.entries.find { it.mean.first == info.first().measureTypeIdx }!!.mean.second,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp,
                    color = Color.Gray
                )
            } else {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row {
                        Text(
                            text = info.minBy { it.number }.number.toString(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Column {
                            Text(text = stringResource(R.string.text_min))
                            Text(text = stringResource(R.string.text_blood_sugar_unit))
                        }
                    }

                    Row {
                        Text(
                            text = info.maxBy { it.number }.number.toString(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Column {
                            Text(text = stringResource(R.string.text_max))
                            Text(text = stringResource(R.string.text_blood_sugar_unit))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ItemBloodSugarTable() {
    
}

@Preview
@Composable
private fun PreviewItemBloodSugarSummaryInfo() {
    YourBodyProfileTheme {
        Column {

            ItemBloodSugarSummaryInfo(
                listOf(
                    BloodSugarInfo(
                        measureDateTime = LocalDateTime.now(),
                        measureTypeIdx = 0,
                        number = 80,
                        memo = null,
                        mealInfo = listOf()
                    )
                )
            )

            ItemBloodSugarSummaryInfo(
                listOf(
                    BloodSugarInfo(
                        measureDateTime = LocalDateTime.now(),
                        measureTypeIdx = 0,
                        number = 80,
                        memo = null,
                        mealInfo = listOf()
                    ),
                    BloodSugarInfo(
                        measureDateTime = LocalDateTime.now(),
                        measureTypeIdx = 0,
                        number = 100,
                        memo = null,
                        mealInfo = listOf()
                    )
                )
            )
        }
    }
}