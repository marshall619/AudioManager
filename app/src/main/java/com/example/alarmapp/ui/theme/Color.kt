package com.example.alarmapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val Colors.simpleBackground : Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF000000) else Color(0xFFF7F7F7)

val Colors.simpleTextColor : Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFFF7F7F7) else Color(0xFF000000)

val Colors.boxColors : Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF232524) else Color(0xFFFAFFF9)

val Colors.boxOnOffColors : Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF0073DD) else Color(0xFF0073DD)

val Colors.boxPlusColors : Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF232524) else Color(0xFFffffff)

val Colors.editTextBackground : Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF242424) else Color(0xFFD5D5D5)

val Colors.addButtonColor : Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF6750A4) else Color(0xFFEADDFF)

val Colors.bottomHomeBrush : List<Color>
    @Composable
    get() = if (isSystemInDarkTheme()) listOf(
        Color(0x0),
        Color(0xff000000)
    ) else listOf(
        Color(0xFFFFFF),
        Color(0xFFFFFFFF)
    )