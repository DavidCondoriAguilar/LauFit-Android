package com.example.gymapp.frontend.ui.pages

import GymAppTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymapp.R
import com.example.gymapp.ui.theme.AppTypography

class TrainersActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GymAppTheme {
                TrainersScreen()
            }
        }
    }
}

@Composable
fun TrainersScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Nuestros Entrenadores",
            style = AppTypography.headlineLarge,
            color = Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Lista de entrenadores
        TrainerCard(
            image = R.drawable.trainer1,
            subtitle = stringResource(id = R.string.trainer_subtitle_personal),
            description = stringResource(id = R.string.trainer_description_personal),
        )

        Spacer(modifier = Modifier.height(16.dp))

        TrainerCard(
            image = R.drawable.nutri1,
            subtitle = stringResource(id = R.string.trainer_subtitle_nutrition),
            description = stringResource(id = R.string.trainer_description_nutrition),
        )

        Spacer(modifier = Modifier.height(16.dp))

        TrainerCard(
            image = R.drawable.yoga1,
            subtitle = stringResource(id = R.string.trainer_subtitle_yoga),
            description = stringResource(id = R.string.trainer_description_yoga),
        )
    }
}

@Composable
fun TrainerCard(image: Int, subtitle: String, description: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(White)
            .padding(16.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(0.5.dp, LightGray, RoundedCornerShape(12.dp))
            .shadow(2.dp, RoundedCornerShape(12.dp))
            .padding(vertical = 12.dp)
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "Imagen del entrenador",
            modifier = Modifier
                .size(80.dp)
                .background(LightGray)
                .clip(RoundedCornerShape(12.dp))
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = subtitle,
            style = AppTypography.headlineMedium,
            color = Black,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = description,
            style = AppTypography.bodyMedium,
            color = DarkGray,
            textAlign = TextAlign.Start
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { /* Manejar la navegación aquí */ },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Ver más")
        }
    }
}
