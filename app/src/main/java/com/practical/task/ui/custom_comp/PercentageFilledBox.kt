package com.practical.task.ui.custom_comp

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practical.task.R
import com.practical.task.ui.theme.TaskTheme
import com.practical.task.ui.theme.colorBlack
import com.practical.task.ui.theme.colorBorderBlack
import com.practical.task.ui.theme.colorRed
import com.practical.task.ui.theme.colorWhite

@Composable
fun PercentageFilledBox(
    percentage: Float, // 0f to 1f
    fillColor: Color = colorRed,
    backgroundColor: Color = colorBlack,
    shape: Shape = RoundedCornerShape(16.dp),
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    val pct = percentage.coerceIn(0f, 1f)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .clip(shape)        // clip container first
            .background(backgroundColor)            // then background
            .border(1.dp, colorBorderBlack, shape=RoundedCornerShape(16.dp)) // border last
    ) {
        // Ensure this Box is anchored to the start
        val fillShape = if (pct < 1f) {
            // Rounded on start only while filling
            RoundedCornerShape(
                topStart = 16.dp,
                bottomStart = 16.dp,
                topEnd = 0.dp,
                bottomEnd = 0.dp
            )
        } else {
            // Fully rounded when 100%
            shape
        }

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(pct)
                .align(Alignment.CenterStart)
                .clip(fillShape)
                .background(fillColor)
        )

        Row(modifier= Modifier.fillMaxSize().padding(horizontal = 20.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(R.drawable.ic_km), contentDescription = "KM")
            Spacer(modifier= Modifier.width(8.dp))
            Text(
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorWhite
                        )
                    ) {
                        append("7015.3")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            baselineShift = BaselineShift(0.1f),
                            color = colorWhite
                        )
                    ) {
                        append("km")
                    }
                },
                style = MaterialTheme.typography.titleLarge
            )

        }
    }
}

@Preview
@Composable
fun PercentageFillBoxPreview(){
    TaskTheme {
        PercentageFilledBox(0.29f)
    }
}
