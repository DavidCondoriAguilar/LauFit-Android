package com.example.gymapp

import GymAppTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GymAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Aquí puedes agregar el contenido, como el carrusel
                    Content(innerPadding)
                }
            }
        }
    }
}

@Composable
fun Content(innerPadding: PaddingValues) {
    // Aquí puedes poner tu carrusel o cualquier otro contenido de la app
}
