package com.practical.task.ui.custom_comp

import android.graphics.RenderEffect
import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun BlurBackgroundBox(
    modifier: Modifier = Modifier,
    blurRadius: Float = 20f,
    fallbackImageRes: Int, // your blur PNG image
    content: @Composable () -> Unit
) {
    Box(modifier = modifier) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            // ✅ Native blur effect for Android 12+
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .graphicsLayer {
                        renderEffect = RenderEffect
                            .createBlurEffect(blurRadius, blurRadius, android.graphics.Shader.TileMode.CLAMP)
                            .asComposeRenderEffect()
                    }
                    .background(Color.Transparent)
            )
        } else {
            // ✅ Fallback blur image for pre-Lollipop or older devices
            Image(
                painter = painterResource(id = fallbackImageRes),
                contentDescription = "blur_background",
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize()
            )
        }

        // Foreground content
        Box(modifier = Modifier.matchParentSize()) {
            content()
        }
    }
}
