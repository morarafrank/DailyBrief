package com.morarafrank.dailybrief.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.morarafrank.dailybrief.R

// Set of Material typography styles to start with

val regularFont = FontFamily(Font(R.font.regular))
val boldFont = FontFamily(Font(R.font.bold))
val italicFont = FontFamily(Font(R.font.italic))
val extraBoldFont = FontFamily(Font(R.font.extrabold))
val mediumFont = FontFamily(Font(R.font.medium))
val semiBoldFont = FontFamily(Font(R.font.semibold))
val Typography = Typography(

    bodySmall = TextStyle(
        fontFamily = regularFont,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = mediumFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),

    bodyLarge = TextStyle(
        fontFamily = semiBoldFont,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)