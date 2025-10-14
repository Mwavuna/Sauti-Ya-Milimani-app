package com.example.sautiyamilimani_test1.presentation.pages.testCocepts

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerPage() {
    val priColor = MaterialTheme.colorScheme.onSurface

    //gradient colors to animate
    val shimmerColors = listOf(
        priColor.copy(0.7f), priColor.copy(0.2f), priColor.copy(0.7f)

    )

    val transition = rememberInfiniteTransition()
    val tranlateAnim = transition.animateFloat(
        initialValue = 0f, targetValue = 1200f, animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 800,
                easing = FastOutSlowInEasing,
            ), repeatMode = RepeatMode.Reverse
        )
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = tranlateAnim.value, y = tranlateAnim.value)
    )

    LazyColumn(
        modifier = Modifier.padding(vertical = 32.dp)
    ) {
        items(7) {
            repeat(7) {
                ShimmerScreen(brush)
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ShimmerScreen(brush: Brush) {


    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Spacer(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(brush)
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(horizontal = 8.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center

        ) {
            Row(
                modifier = Modifier
                    .size(width = 200.dp, height = 30.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(brush)
            ) {}
            Spacer(
                modifier = Modifier.height(10.dp)
            )
            Spacer(
                modifier = Modifier
                    .size(width = 300.dp, height = 30.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(brush)
            )

        }

    }


}