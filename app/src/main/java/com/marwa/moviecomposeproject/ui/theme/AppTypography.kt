package com.marwa.moviecomposeproject.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.marwa.moviecomposeproject.R

object AppTypography {
    val appTitle = TextStyle(
        fontFamily = FontFamily(Font(R.font.merriweather_black)),
        fontWeight = FontWeight.Black,
        color = titleColor,
        fontSize = 16.sp,
        letterSpacing = 0.15.sp
    )

    val movieTitle = TextStyle(
        fontFamily = FontFamily(Font(R.font.mulish_bold)),
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        fontSize = 14.sp,
        letterSpacing = 0.15.sp
    )

    val ratingStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.mulish)),
        fontWeight = FontWeight.Normal,
        color = ratingColor,
        fontSize = 12.sp,
        letterSpacing = 0.15.sp
    )

    val dateStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.mulish)),
        fontWeight = FontWeight.Normal,
        color = Color.Black,
        fontSize = 14.sp,
        letterSpacing = 0.15.sp
    )
}