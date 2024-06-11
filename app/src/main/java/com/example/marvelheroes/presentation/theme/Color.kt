package com.example.marvelheroes.presentation.theme

import androidx.compose.ui.graphics.Color

//dark theme
val DarkGrey = Color(0xff2b272b)
val RedDark = Color(0xFF8B0000)
val Silver = Color(0xFFC0C0C0)

//light theme
val LightPink = Color(0xFFFFB6C1)
val Orange = Color(0xFFFFA500)
val Black = Color(0xFF000000)

sealed class ThemeColors(
    val primary: Color,
    val secondary: Color,
    val tertiary: Color
)  {
    data object Light: ThemeColors(
        primary = Orange,
        secondary = LightPink,
        tertiary = Black
    )
    data object Dark: ThemeColors(
        primary = DarkGrey,
        secondary = RedDark,
        tertiary = Silver
    )
}

