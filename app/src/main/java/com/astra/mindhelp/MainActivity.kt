package com.astra.mindhelp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.astra.mindhelp.ui.theme.MindHelpTheme
import java.util.Calendar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MindHelpTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MindHelpMainScreen(userName = "An")
                }
            }
        }
    }
}

@Composable
fun MindHelpMainScreen(userName: String) {
    val now = Calendar.getInstance()
    val hour = now.get(Calendar.HOUR_OF_DAY)
    val greeting = when (hour) {
        in 5..11 -> "Good morning, $userName"
        in 12..17 -> "Good afternoon, $userName"
        in 18..21 -> "Good evening, $userName"
        else -> "Good night, $userName"
    }
    val pastelBlue = Color(0xFFE3F2FD)
    val pastelMint = Color(0xFFE0F7FA)
    val pastelSurface = MaterialTheme.colorScheme.surface
    var selectedMood by remember { mutableStateOf(-1) }
    val moods = listOf(
        "😊" to "Happy",
        "😔" to "Sad",
        "😐" to "Neutral",
        "😰" to "Anxious",
        "😡" to "Angry"
    )
    val featureCards: List<Triple<String, androidx.compose.ui.graphics.vector.ImageVector, Color>> = listOf(
        Triple("Talk to AI", Icons.Filled.Face, Color(0xFFB3E5FC)),
        Triple("Quick Meditation", Icons.Filled.Star, Color(0xFFB2DFDB)),
        Triple("Relaxing Sounds", Icons.Filled.Email, Color(0xFFB3E5FC)),
        Triple("Mood Tracker", Icons.Filled.Home, Color(0xFFC8E6C9))
    )
    val dailyTip = "Try today’s 5-minute letting-go meditation"
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(pastelBlue, pastelMint, pastelSurface)
                )
            )
            .padding(20.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(Modifier.height(8.dp))
            Text(
                text = greeting,
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            )
            // Mood check-in
            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.7f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("How are you feeling today?", fontWeight = FontWeight.Medium)
                    Spacer(Modifier.height(8.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        moods.forEachIndexed { idx, (emoji, label) ->
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .padding(4.dp)
                                    .clickable { selectedMood = idx }
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(48.dp)
                                        .background(
                                            if (selectedMood == idx) Color(0xFFB2EBF2) else Color.Transparent,
                                            shape = CircleShape
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(emoji, fontSize = 28.sp)
                                }
                                Text(label, fontSize = 12.sp)
                            }
                        }
                    }
                }
            }
            // Feature cards
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                featureCards.chunked(2).forEach { rowCards: List<Triple<String, androidx.compose.ui.graphics.vector.ImageVector, Color>> ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        rowCards.forEach { card ->
                            val (title, icon, bgColor) = card
                            Card(
                                shape = RoundedCornerShape(20.dp),
                                colors = CardDefaults.cardColors(containerColor = bgColor.copy(alpha = 0.8f)),
                                modifier = Modifier
                                    .weight(1f)
                                    .height(90.dp)
                                    .clickable { /* TODO: Navigate to feature */ }
                            ) {
                                Column(
                                    Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Icon(icon, contentDescription = title, tint = Color(0xFF01579B), modifier = Modifier.size(32.dp))
                                    Spacer(Modifier.height(6.dp))
                                    Text(title, fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
                                }
                            }
                        }
                        if (rowCards.size == 1) Spacer(Modifier.weight(1f))
                    }
                }
            }
            // Daily tip
            Card(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.8f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Filled.Favorite, contentDescription = null, tint = Color(0xFF4DD0E1))
                    Spacer(Modifier.width(10.dp))
                    Text(dailyTip, fontWeight = FontWeight.Medium)
                }
            }
            // Lottie animation placeholder
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("[Lottie Animation Here]", color = Color(0xFFB3E5FC))
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MindHelpTheme {
        Greeting("Android")
    }
}

