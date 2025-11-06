package com.practical.task.ui.screens.upcoming_race_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.practical.task.R
import com.practical.task.ui.custom_comp.TextBold3x
import com.practical.task.ui.custom_comp.TextMediumNormal
import com.practical.task.ui.custom_comp.TextRegularNormal
import com.practical.task.ui.custom_comp.TextSemiBoldNormal
import com.practical.task.ui.theme.TaskTheme
import com.practical.task.ui.theme.colorBlack
import com.practical.task.ui.theme.colorDivider
import com.practical.task.ui.theme.colorMediumGreen

@Composable
fun UpComingRaceScreen(navController: NavHostController) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = colorBlack)) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop,
            painter = painterResource(R.drawable.bg_top_details),
            contentDescription = "Top Gradient Background"
        )
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp),
                    contentAlignment = Alignment.Center
                ) {
                    TextBold3x("Upcoming race")
                }

                Spacer(modifier = Modifier.height(40.dp))

                Row(
                    modifier = Modifier.padding(horizontal = 15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        TextSemiBoldNormal("Round 12")
                        Spacer(modifier = Modifier.height(2.dp))
                        TextBold3x("São Paulo GP", fontSize = 22.sp)
                        Spacer(modifier = Modifier.height(2.dp))
                        TextSemiBoldNormal("São Paulo", color = colorMediumGreen)
                        Spacer(modifier = Modifier.height(2.dp))
                        TextSemiBoldNormal("23 - 30 April")
                        Spacer(modifier = Modifier.height(30.dp))
                        TextMediumNormal("FP1 Starts in", fontSize = 10.sp)
                        Spacer(modifier = Modifier.height(2.dp))
                        Row {
                            TimeDayHrMin("07", "Days")
                            Spacer(modifier = Modifier.width(15.dp))
                            TimeDayHrMin("16", "Hours")
                            Spacer(modifier = Modifier.width(15.dp))
                            TimeDayHrMin("42", "Minutes")
                        }
                    }

                    Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                        Image(
                            painter = painterResource(R.drawable.img_3d_circuit_3x),
                            contentDescription = "3d Circuit"
                        )
                    }
                }

                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)) {
                    Spacer(modifier = Modifier.height(40.dp))
                    TextBold3x("São Paulo Circuit")
                    Spacer(modifier = Modifier.height(10.dp))
                    TextRegularNormal("Bahrain International circuit is located in Sakhir, Bahrain and it was designed by German architect Hermann Tilke. It was built on the site of a former camel farm, in Sakhir. It measures 5.412 km, has 15 corners and 3 DRS Zones. The Grand Prix have 57 laps. This circuit has 6 alternative layouts.")

                    Spacer(modifier = Modifier.height(30.dp))
                    TextBold3x("Circuit Facts")
                    Spacer(modifier = Modifier.height(15.dp))

                    TextRegularNormal("His brother Arthur Leclerc is currently set to race for DAMS in the 2023 F2 Championship")
                    Spacer(modifier = Modifier.height(15.dp))
                    HorizontalDivider(thickness = 2.dp, color = colorDivider)
                    Spacer(modifier = Modifier.height(15.dp))
                    TextRegularNormal("He’s not related to Édouard Leclerc, the founder of a French supermarket chain")
                    Spacer(modifier = Modifier.height(15.dp))
                    HorizontalDivider(thickness = 2.dp, color = colorDivider)
                }
            }
        }
    }
}

@Composable
fun TimeDayHrMin(dhm: String, lbl: String) {
    Text(
        buildAnnotatedString {
            withStyle(
                style = MaterialTheme.typography.labelLarge.toSpanStyle().copy(
                    fontSize = 28.sp,
                    fontWeight = FontWeight.W500,
                    color = colorMediumGreen
                )
            ) {
                append(dhm)
            }
            withStyle(
                style = MaterialTheme.typography.labelSmall.toSpanStyle().copy(
                    fontSize = 8.sp,
                    baselineShift = BaselineShift(0.1f), // ✅ aligns "AM" slightly higher
                )
            ) {
                append("\n$lbl")
            }
        }
    )
}

@Preview
@Composable
fun UpComingRaceScreenPreview() {
    TaskTheme {
        UpComingRaceScreen(NavHostController(LocalContext.current))
    }
}