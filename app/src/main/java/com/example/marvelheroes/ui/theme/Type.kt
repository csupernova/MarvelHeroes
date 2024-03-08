package com.example.marvelheroes.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.marvelheroes.R

val skModernistFamily = FontFamily(
    Font(R.font.sk_modernist_regular, FontWeight.Normal),
    Font(R.font.sk_modernist_bold, FontWeight.Bold)
)

val Typography = Typography(

    titleLarge = TextStyle(
        fontFamily = skModernistFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
    ),

    bodyMedium = TextStyle(
        fontFamily = skModernistFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 25.sp,
        lineHeight = 25.sp,
    )
)
