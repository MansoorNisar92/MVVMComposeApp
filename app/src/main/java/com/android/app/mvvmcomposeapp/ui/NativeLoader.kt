package com.android.app.mvvmcomposeapp.ui

import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun NativeLoader() {
    val progressBarSize = with(LocalDensity.current) {
        50.dp.toPx()
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        AndroidView(
            factory = { context ->
                ProgressBar(context).apply {
                    isIndeterminate = true
                    layoutParams = FrameLayout.LayoutParams(
                        progressBarSize.toInt(),
                        progressBarSize.toInt()
                    )
                }
            },
            modifier = Modifier.size(100.dp)
        )
    }
}