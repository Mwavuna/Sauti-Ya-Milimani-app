package com.example.sautiyamilimani_test1.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography = Typography(
    headlineLarge = TextStyle( // h1
        fontSize = 24.sp,      // text-2xl ~ 1.5rem
        fontWeight = FontWeight.Medium,
        lineHeight = 36.sp
    ),
    headlineMedium = TextStyle( // h2
        fontSize = 20.sp,      // text-xl ~ 1.25rem
        fontWeight = FontWeight.Medium,
        lineHeight = 28.sp
    ),
    headlineSmall = TextStyle( // h3
        fontSize = 18.sp,      // text-lg ~ 1.125rem
        fontWeight = FontWeight.Medium,
        lineHeight = 28.sp
    ),
    bodyLarge = TextStyle(
        fontSize = 16.sp,      // text-base ~ 1rem
        fontWeight = FontWeight.Normal,
        lineHeight = 24.sp
    ),
    bodyMedium = TextStyle(
        fontSize = 14.sp,      // text-sm ~ 0.875rem
        fontWeight = FontWeight.Normal,
        lineHeight = 20.sp
    ),
    bodySmall = TextStyle(
        fontSize = 12.sp,      // text-xs ~ 0.75rem
        fontWeight = FontWeight.Normal,
        lineHeight = 16.sp
    )
)
