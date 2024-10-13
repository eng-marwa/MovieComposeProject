package com.marwa.moviecomposeproject.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter

@Composable
fun AppBarIcon(modifier: Modifier, icon: Painter, description: String, iconColor: ColorFilter? = null) {
    Image(
        painter = icon, colorFilter = iconColor,
        contentDescription = description,
        modifier = modifier,
    )
}