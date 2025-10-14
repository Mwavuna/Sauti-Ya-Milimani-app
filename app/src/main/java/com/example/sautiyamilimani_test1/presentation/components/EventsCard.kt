package com.example.sautiyamilimani_test1.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FundraisingCard(amount: Int) {
    // Pick background color based on the target amount
    val cardColor = when (amount) {
        200 -> Color(0xFFFF9800) // Orange
        300 -> Color(0xFF2196F3) // Blue
        500 -> Color(0xFF9C27B0) // Purple
        1000 -> Color(0xFFF44336) // Red
        else -> MaterialTheme.colorScheme.surface
    }

    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        Box(
            modifier = Modifier
                .background(cardColor)
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "✨ MWALIKO WA KUCHANGIA UJENZI WA KANISA ✨",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = "Ungana nasi katika harambee ya kujenga kanisa la Sauti ya Mlimani.",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp
                )

                Spacer(Modifier.height(16.dp))

                Text(
                    text = "📅 Tarehe: 11 Oktoba 2025",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = "📍 Mahali: Kanisa la Sauti ya Mlimani",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = "👤 Mwandaji: Elisha Mumba",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(Modifier.height(20.dp))

                Text(
                    text = "🎯 Kiasi cha Kuchangia: Ksh $amount",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = "Karibu tushirikiane kujenga nyumba ya Mungu!",
                    color = Color.White,
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFundraisingCard() {
    Column {
        FundraisingCard(200)
        FundraisingCard(300)
        FundraisingCard(500)
        FundraisingCard(1000)
    }
}
