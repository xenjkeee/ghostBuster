package com.example.impulsetesttask.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.impulsetesttask.R

private val fontFamily = FontFamily(
    Font(R.font.inter_black, weight = FontWeight.Black),
    Font(R.font.inter_bold, weight = FontWeight.Bold),
    Font(R.font.inter_extra_bold, weight = FontWeight.ExtraBold),
    Font(R.font.inter_extra_light, weight = FontWeight.ExtraLight),
    Font(R.font.inter_light, weight = FontWeight.Light),
    Font(R.font.inter_medium, weight = FontWeight.Medium),
    Font(R.font.inter_regular),
    Font(R.font.inter_semi_bold, weight = FontWeight.SemiBold),
    Font(R.font.inter_thin, weight = FontWeight.Thin),
)

// Set of Material typography styles to start with
val Typography = Typography(
    defaultFontFamily = fontFamily,
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)