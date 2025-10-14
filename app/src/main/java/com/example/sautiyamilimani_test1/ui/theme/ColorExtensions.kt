package com.example.sautiyamilimani_test1.ui.theme


import android.graphics.Color
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable

val ColorScheme.isLight: Boolean
    get() = this == androidx.compose.material3.lightColorScheme()


val ColorScheme.mutedForeground: androidx.compose.ui.graphics.Color
    @Composable get() = if (isLight) LightMutedForeground else DarkMutedForeground

val ColorScheme.foreground:  androidx.compose.ui.graphics.Color
    @Composable get() = if (isLight) LightForeground else DarkForeground
val ColorScheme.muted: androidx.compose.ui.graphics.Color
    @Composable get() = if (isLight) LightMuted else DarkMuted

val ColorScheme.popover: androidx.compose.ui.graphics.Color
    @Composable get() = if (isLight) LightPopover else DarkPopover

val ColorScheme.sidebar: androidx.compose.ui.graphics.Color
    @Composable get() = if (isLight) LightSidebar else DarkSidebar

val ColorScheme.sidebarForeground: androidx.compose.ui.graphics.Color
    @Composable get() = if (isLight) LightSidebarForeground else DarkSidebarForeground

val ColorScheme.chart1: androidx.compose.ui.graphics.Color
    @Composable get() = if (isLight) LightChart1 else DarkChart1

val ColorScheme.chart2: androidx.compose.ui.graphics.Color
    @Composable get() = if (isLight) LightChart2 else DarkChart2

val ColorScheme.chart3: androidx.compose.ui.graphics.Color
    @Composable get() = if (isLight) LightChart3 else DarkChart3

val ColorScheme.chart4: androidx.compose.ui.graphics.Color
    @Composable get() = if (isLight) LightChart4 else DarkChart4

val ColorScheme.chart5: androidx.compose.ui.graphics.Color
    @Composable get() = if (isLight) LightChart5 else DarkChart5

val ColorScheme.teal500: androidx.compose.ui.graphics.Color
    @Composable get() = if (isLight) LightTeal500 else DarkTeal500

val ColorScheme.blue500: androidx.compose.ui.graphics.Color
    @Composable get() = if (isLight) LightBlue500 else DarkBlue500

val ColorScheme.purple50: androidx.compose.ui.graphics.Color
    @Composable get() = if (isLight) LightPurple50 else DarkPurple50

val ColorScheme.pink500: androidx.compose.ui.graphics.Color
    @Composable get() = if (isLight) LightPink500 else DarkPink500

val ColorScheme.green50: androidx.compose.ui.graphics.Color
    @Composable get() = if (isLight) LightGreen50 else DarkGreen50
