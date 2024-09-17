package com.example.gymapp.frontend.ui.splash

import GymAppTheme
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.gymapp.R
import com.example.gymapp.frontend.ui.login.LoginActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GymAppTheme {
                // Surface with a gradient background
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black // Set a base color for the background
                ) {
                    SplashScreen()
                }
            }
        }

        // Redirect to LoginActivity after 2 seconds
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() // Finish SplashActivity to prevent returning to it
        }, 2000)
    }
}

@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color.Black, Color.DarkGray),
                    start = androidx.compose.ui.geometry.Offset(0f, 0f),
                    end = androidx.compose.ui.geometry.Offset(1000f, 1000f)
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo), // Ensure the resource name is correct
            contentDescription = null,
            modifier = Modifier
                .size(500.dp) // Adjust size for better fit
                .graphicsLayer {
                    shadowElevation = 8.dp.toPx() // Add shadow effect
                    shape = RoundedCornerShape(16.dp) // Rounded corners for the image
                    clip = true
                }
                .background(Color.White.copy(alpha = 0.6f), shape = RoundedCornerShape(16.dp)) // Background with slight transparency
                .padding(16.dp), // Padding around the image
            contentScale = ContentScale.Inside // Adjust how the image scales to fit within bounds
        )
    }
}
