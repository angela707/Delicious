package com.panini.core.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF75A89D),
    secondary = Color(0xFF6A63B8),
    tertiary = Color(0xFF6F9D94),
    background = Color(0xFF2A2826),
    surface = Color(0xFF3B3937),
    onPrimary = Color.White,
    onSecondary = Color(0xFFB3E5FC),
    onTertiary = Color(0xFFEFEDEC),
    onBackground = Color.White,
    onSurface = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF89E1CF),
    secondary = Color(0xFFA49EF4),
    tertiary = Color(0xFF96C9B7),
    background = Color(0xFFEFEDEC),
    surface = Color(0xFFFFFFFF),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color(0xFF706E6B),
    onBackground = Color.Black,
    onSurface = Color.Black
)

@Composable
fun DeliciousTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}