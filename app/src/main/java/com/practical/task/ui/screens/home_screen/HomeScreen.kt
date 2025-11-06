package com.practical.task.ui.screens.home_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.practical.task.R
import com.practical.task.core.extension.formatSessionTime
import com.practical.task.core.extension.launchScreen
import com.practical.task.data.UiState
import com.practical.task.data.model.DriverItem
import com.practical.task.data.model.DriverResponse
import com.practical.task.data.model.Race
import com.practical.task.data.model.ScheduleResponse
import com.practical.task.navigation.Screen
import com.practical.task.ui.custom_comp.LinearGradientText
import com.practical.task.ui.custom_comp.PercentageFilledBox
import com.practical.task.ui.custom_comp.TextBold2x
import com.practical.task.ui.custom_comp.TextBoldNormal
import com.practical.task.ui.custom_comp.TextMediumNormal
import com.practical.task.ui.theme.TaskTheme
import com.practical.task.ui.theme.brighterColor
import com.practical.task.ui.theme.colorBlack
import com.practical.task.ui.theme.colorBorderBlack
import com.practical.task.ui.theme.colorDarkGreen
import com.practical.task.ui.theme.colorOrange
import com.practical.task.ui.theme.colorWhiteOpacity10
import com.tbuonomo.viewpagerdotsindicator.compose.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.compose.model.DotGraphic
import com.tbuonomo.viewpagerdotsindicator.compose.type.ShiftIndicatorType
import kotlinx.coroutines.delay

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun HomeScreen(
    navController: NavHostController,
    innerPadding: PaddingValues,
    viewModel: HomeViewModel = hiltViewModel()
) {
    var pageCount by remember { mutableIntStateOf(2) }
    val pagerState = rememberPagerState { pageCount }

    val uiState by viewModel.uiState.collectAsState()
    val uiStateSchedules by viewModel.uiStateSchedules.collectAsState()

    val driverData = when (uiState) {
        is UiState.Success -> {
            (uiState as UiState.Success<DriverResponse>).data.drivers.firstOrNull { v -> v.position == 1 }
        }

        else -> null
    }

    val races = when (uiStateSchedules) {
        is UiState.Success -> {
            (uiStateSchedules as UiState.Success<ScheduleResponse>).data.schedule
        }

        else -> null
    }

    val currentTime = System.currentTimeMillis() / 1000 // current Unix time in seconds
    val upcomingRace = races
        ?.filter { it.raceState != "completed" && it.raceStartTime > currentTime } // race not started yet
        ?.minByOrNull { it.raceStartTime } // earliest future race

    LaunchedEffect(pagerState) {
        while (true) {
            delay(3000) // ⏱ every 3 seconds
            val nextPage = (pagerState.currentPage + 1) % pageCount
            pagerState.animateScrollToPage(nextPage)
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(colorBlack)
            .padding(bottom = 5.dp)
    ) {
        item {
            TopLayerUi(pagerState,  driverData, pageCount)
            BottomLayerUi(upcomingRace, uiState, uiStateSchedules, navController)
        }
    }

    if (uiState is UiState.Loading || uiStateSchedules is UiState.Loading) Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LoaderView()
    }
}

@Composable
private fun LoaderView() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorDarkGreen.copy(alpha = 0.4f))
            .blur(8.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(60.dp),
            strokeCap = ProgressIndicatorDefaults.CircularIndeterminateStrokeCap, // expressive style
            strokeWidth = 4.dp,
            color = colorOrange.copy(alpha = 0.5f)
        )
    }
}

@Composable
private fun BottomLayerUi(
    upcomingRace: Race?,
    uiState: UiState<DriverResponse>,
    uiStateSchedules: UiState<ScheduleResponse>,
    navController: NavHostController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(horizontal = 16.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp) // spacing between them
    ) {
        val date =
            if (upcomingRace != null) formatSessionTime(upcomingRace.raceStartTime).dateLabel else "04 Friday"
        val time =
            if (upcomingRace != null) formatSessionTime(upcomingRace.raceStartTime).timeLabel else "08:00 AM"
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .clip(RoundedCornerShape(16.dp)) // ✅ Clip shape
                .background(colorDarkGreen)
                .clickable {
                    if (uiState !is UiState.Loading && uiStateSchedules !is UiState.Loading) {
                        navController.launchScreen(Screen.UpComingScreen(""))
                    }
                }
                .padding(10.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextBoldNormal(upcomingRace?.sessions[0]?.sessionName ?: "FP1", fontSize = 12.sp)
                    Image(
                        painter = painterResource(R.drawable.img_circuit),
                        contentDescription = null,
                        Modifier.size(44.dp)
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(R.drawable.ic_calender),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    TextBoldNormal(date)
                }
                Text(
                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontSize = 36.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF00FF99)
                            )
                        ) {
                            append(time.split(" ")[0])
                        }
                        withStyle(
                            style = SpanStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                baselineShift = BaselineShift(0.1f), // ✅ aligns "AM" slightly higher
                                color = Color(0xFF00FF99)
                            )
                        ) {
                            append(" ${time.split(" ")[1].uppercase()}")
                        }
                    },
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            PercentageFilledBox(
                percentage = 0.35f, modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(16.dp))        // clip container first
                    .background(colorBlack)            // then background
                    .border(
                        1.dp,
                        colorBorderBlack,
                        shape = RoundedCornerShape(16.dp)
                    ) // border last)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(16.dp)) // ✅ Clip shape
                    .background(Color.Blue),
                contentAlignment = Alignment.TopEnd
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 15.dp, end = 5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_eduction),
                        contentDescription = "education"
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(
                        buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                )
                            ) {
                                append("Formula 1")
                            }
                            withStyle(
                                style = SpanStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    baselineShift = BaselineShift(0.1f),
                                )
                            ) {
                                append("\nEducation")
                            }
                        },
                        style = MaterialTheme.typography.titleLarge
                    )
                }
                Image(
                    painter = painterResource(R.drawable.ic_redirect),
                    contentDescription = "redirect",
                    modifier = Modifier.padding(end = 8.dp, top = 8.dp)
                )
            }
        }
    }

    Image(
        painter = painterResource(id = R.drawable.img_instagram), // ✅ local image
        contentDescription = "Instagram Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(24.dp))
    )
}

@Composable
private fun LazyItemScope.TopLayerUi(
    pagerState: PagerState,
    driverData: DriverItem?,
    pageCount: Int
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillParentMaxHeight(0.52f)
            .background(color = Color.Red)
    ) {
        HorizontalPager(
            modifier = Modifier.fillMaxSize(),
            state = pagerState
        ) { page ->
            when (page) {
                0 -> FirstPage(driverData)
                else -> SecondPage()
            }
        }

        DotsIndicator(
            dotCount = pageCount,
            type = ShiftIndicatorType(
                dotsGraphic = DotGraphic(
                    color = Color.White,
                    size = 5.dp
                ), shiftSizeFactor = 6.0f
            ),
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.BottomCenter) // ✅ bottom center alignment
                .padding(bottom = 16.dp)       // add a little space from bottom
        )
    }
}

@Composable
fun RowAlignIconWithText(drawableResource: Int, txt: String = "01", posFix: String = "Pos") {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(drawableResource),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            buildAnnotatedString {
                withStyle(
                    style = MaterialTheme.typography.titleMedium.toSpanStyle().copy(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W700
                    )
                ) {
                    append(txt)
                }
                withStyle(
                    style = MaterialTheme.typography.labelSmall.toSpanStyle().copy(
                        fontSize = 10.sp,
                        fontWeight = FontWeight.W500,
                        baselineShift = BaselineShift(0.1f), // ✅ aligns "AM" slightly higher
                    )
                ) {
                    append("  $posFix")
                }
            }
        )
    }
}

@Composable
fun FirstPage(driverData: DriverItem?) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorOrange),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 30.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp)) // ✅ Clip shape
                    .background(colorWhiteOpacity10)
                    .padding(horizontal = 10.dp, vertical = 5.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.img_get_pro_3x),
                    contentDescription = null
                )
            }

            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(R.drawable.img_txt_lando),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(start = 20.dp, top = 5.dp),
                    contentDescription = null,
                )

                Image(
                    painter = painterResource(R.drawable.img_lando_norris_3x),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(bottom = 40.dp, end = 20.dp, start = 20.dp)
                ) {
                    Row {
                        RowAlignIconWithText(
                            drawableResource = R.drawable.ic_brs,
                            if (driverData != null) convertTwoDigitString(driverData.position) else "01",
                            "Pos"
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                        RowAlignIconWithText(
                            drawableResource = R.drawable.ic_wins,
                            if (driverData != null) convertTwoDigitString(driverData.wins) else "09",
                            "Wins"
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    Row(verticalAlignment = Alignment.Bottom) {
                        LinearGradientText(driverData?.points?.toString() ?: "429")
                        Spacer(modifier = Modifier.width(8.dp))
                        Box(
                            modifier = Modifier
                                .padding(bottom = 20.dp)
                                .clip(RoundedCornerShape(5.dp)) // ✅ Clip shape
                                .background(colorOrange)
                                .padding(horizontal = 8.dp, vertical = 1.dp)
                        ) {
                            TextMediumNormal("PTS")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SecondPage() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorBlack),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 30.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp)) // ✅ Clip shape
                    .background(colorWhiteOpacity10)
                    .padding(horizontal = 10.dp, vertical = 5.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.img_get_pro_3x),
                    contentDescription = null
                )
            }

            Image(
                painter = painterResource(R.drawable.img_just_an_app),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 10.dp)
            )

            Button(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 15.dp),
                colors = ButtonColors(
                    containerColor = brighterColor,
                    contentColor = colorBlack,
                    disabledContentColor = colorBlack,
                    disabledContainerColor = colorBlack
                ),
                onClick = {

                }
            ) {
                TextBold2x("Follow Us", color = colorBlack)
            }
        }
    }
}

fun convertTwoDigitString(num: Int): String {
    return if (num < 10) "0$num" else "$num"
}

@Preview
@Composable
fun HomeScreenPreview() {
    TaskTheme {
        HomeScreen(navController = NavHostController(LocalContext.current), PaddingValues(0.dp))
    }
}