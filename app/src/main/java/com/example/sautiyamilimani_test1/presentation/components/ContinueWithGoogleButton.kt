package com.example.sautiyamilimani_test1.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sautiyamilimani_test1.R

@Composable
fun GoogleSignInButton(
    onClick: () -> Unit,
    isDarkTheme: Boolean = isSystemInDarkTheme() // detect theme
) {
    val backgroundColor = if (isDarkTheme) Color(0xFF131314) else Color.White
    val textColor = if (isDarkTheme) Color.White else Color(0xFF3C4043)
    val borderColor = if (isDarkTheme) Color(0xFF5F6368) else Color(0xFFDADCE0)
    val googleIcon = if (isDarkTheme)
        painterResource(R.drawable.google_icon) // inverted logo
    else
        painterResource(R.drawable.google_icon) // normal logo

    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = Color.Unspecified
        ),
        border = BorderStroke(1.dp, borderColor),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 1.dp)
    ) {
        CompositionLocalProvider(LocalContentColor provides Color.Unspecified) {
            Image(
                painter = googleIcon,
                contentDescription = "Google logo",
                modifier = Modifier.size(20.dp)
            )
        }
        Spacer(Modifier.width(12.dp))
        Text(
            text = "Continue with Google",
            color = textColor,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
