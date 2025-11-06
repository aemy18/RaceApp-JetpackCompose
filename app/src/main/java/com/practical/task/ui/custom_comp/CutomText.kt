package com.practical.task.ui.custom_comp

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.practical.task.ui.theme.colorOrange
import com.practical.task.ui.theme.colorWhite

@Composable
fun LinearGradientText(txt: String) {
    Text(
        text = txt,
        style = MaterialTheme.typography.displayLarge.copy(
            fontWeight = FontWeight.W300,
            fontSize = 72.sp,
            brush = Brush.linearGradient(
                colors = listOf(
                    colorWhite,
                    colorOrange
                ),
                start = Offset(0f, 0f),      // 🔹 Start at top
                end = Offset(0f, Float.POSITIVE_INFINITY) // 🔹 End at bottom
            ),
        )
    )

}

@Composable
fun TextBold3x(txt: String, fontSize: TextUnit = 18.sp, fontWeight: FontWeight = FontWeight.W700) {
    Text(
        txt,
        style = MaterialTheme.typography.titleLarge.copy(
            fontSize = fontSize,
            fontWeight = fontWeight
        )
    )
}

@Composable
fun TextBold2x(txt: String, fontSize: TextUnit = 16.sp,color: Color = colorWhite) {
    Text(
        txt, style = MaterialTheme.typography.titleMedium.copy(fontSize = fontSize, fontWeight = FontWeight.W700, color = color)
    )
}

@Composable
fun TextBoldNormal(txt: String, fontSize: TextUnit = 14.sp) {
    Text(
        txt, style = MaterialTheme.typography.titleSmall.copy(fontSize = fontSize)
    )
}

@Composable
fun TextRegular3x(txt: String, fontSize: TextUnit = 18.sp, color: Color = colorWhite) {
    Text(
        txt,
        style = MaterialTheme.typography.bodySmall.copy(
            fontSize = fontSize,
            fontWeight = FontWeight.Normal,
            color = color
        )
    )
}

@Composable
fun TextRegular2x(txt: String, fontSize: TextUnit = 16.sp, color: Color = colorWhite) {
    Text(
        txt,
        style = MaterialTheme.typography.bodySmall.copy(
            fontSize = fontSize,
            fontWeight = FontWeight.Normal,
            color = color
        )
    )
}

@Composable
fun TextRegularNormal(txt: String, fontSize: TextUnit = 14.sp, color: Color = colorWhite) {
    Text(
        txt,
        style = MaterialTheme.typography.bodySmall.copy(
            fontSize = fontSize,
            fontWeight = FontWeight.Normal,
            color = color
        )
    )
}


@Composable
fun TextSemiBoldNormal(txt: String, fontSize: TextUnit = 14.sp, color: Color = colorWhite) {
    Text(
        txt,
        style = MaterialTheme.typography.bodySmall.copy(
            fontSize = fontSize,
            fontWeight = FontWeight.SemiBold,
            color = color
        )
    )
}


@Composable
fun TextMediumNormal(txt: String, fontSize: TextUnit = 14.sp, color: Color = colorWhite) {
    Text(
        txt,
        style = MaterialTheme.typography.bodySmall.copy(
            fontSize = fontSize,
            fontWeight = FontWeight.W500,
            color = color
        )
    )
}
