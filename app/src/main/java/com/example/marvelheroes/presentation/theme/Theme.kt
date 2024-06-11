package com.example.marvelheroes.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = ThemeColors.Dark.primary,
    secondary = ThemeColors.Dark.secondary,
    tertiary = ThemeColors.Dark.tertiary

)

private val LightColorScheme = lightColorScheme(
    primary = ThemeColors.Light.primary,
    secondary = ThemeColors.Light.secondary,
    tertiary = ThemeColors.Light.tertiary

)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (!darkTheme) {
        LightColorScheme
    }
    else {
        DarkColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}