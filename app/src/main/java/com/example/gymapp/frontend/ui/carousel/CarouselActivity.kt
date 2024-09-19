package com.example.gymapp.frontend.ui.carousel

import GymAppTheme
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Scaffold
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import com.example.gymapp.R
import com.example.gymapp.frontend.ui.pages.FunctionalActivity
import com.example.gymapp.frontend.ui.pages.NutricionActivity
import com.example.gymapp.frontend.ui.pages.TrainersActivity
import com.example.gymapp.ui.theme.AppTypography

class CarouselActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GymAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CarouselScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CarouselScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .background(Color.White)
    ) {
        Carousel()
        Spacer(modifier = Modifier.height(24.dp))
        TrainersSection()
        Footer()
    }
}

@Composable
fun Carousel(modifier: Modifier = Modifier) {
    val imageList = listOf(
        R.drawable.caraousel1,
        R.drawable.caraouosel2,
        R.drawable.carousel3
    )

    val textList = listOf(
        stringResource(id = R.string.carousel_text_1),
        stringResource(id = R.string.carousel_text_2),
        stringResource(id = R.string.carousel_text_3)
    )

    var currentPage by remember { mutableIntStateOf(0) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(0.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .pointerInput(Unit) {
                    detectHorizontalDragGestures { change, dragAmount ->
                        change.consume()
                        if (dragAmount > 50 && currentPage > 0) {
                            currentPage -= 1
                        } else if (dragAmount < -50 && currentPage < imageList.size - 1) {
                            currentPage += 1
                        }
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = imageList[currentPage]),
                contentDescription = "Imagen de carrusel",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clip(RoundedCornerShape(16.dp))
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Anterior",
                    tint = if (currentPage > 0) Color.Black else Color.Gray,
                    modifier = Modifier
                        .size(40.dp)
                        .clickable(enabled = currentPage > 0) {
                            currentPage -= 1
                        }
                )
                Icon(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = "Siguiente",
                    tint = if (currentPage < imageList.size - 1) Color.Black else Color.Gray,
                    modifier = Modifier
                        .size(40.dp)
                        .clickable(enabled = currentPage < imageList.size - 1) {
                            currentPage += 1
                        }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = textList[currentPage],
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            style = AppTypography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            imageList.forEachIndexed { index, _ ->
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Indicador",
                    tint = if (index == currentPage) Color.Red else Color.LightGray,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { currentPage = index }
                )
            }
        }
    }
}

@Composable
fun TrainersSection(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Nuestros Entrenadores",
            style = AppTypography.headlineLarge,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TrainerItem(
            image = R.drawable.trainer1,
            subtitle = stringResource(id = R.string.trainer_subtitle_personal),
            description = stringResource(id = R.string.trainer_description_personal),
            context = context,
            targetActivity = FunctionalActivity::class.java
        )

        Spacer(modifier = Modifier.height(16.dp))

        TrainerItem(
            image = R.drawable.nutri1,
            subtitle = stringResource(id = R.string.trainer_subtitle_nutrition),
            description = stringResource(id = R.string.trainer_description_nutrition),
            context = context,
            targetActivity = NutricionActivity::class.java
        )

        Spacer(modifier = Modifier.height(16.dp))

        TrainerItem(
            image = R.drawable.yoga1,
            subtitle = stringResource(id = R.string.trainer_subtitle_yoga),
            description = stringResource(id = R.string.trainer_description_yoga),
            context = context,
            targetActivity = TrainersActivity::class.java
        )
    }
}

@Composable
fun TrainerItem(image: Int, subtitle: String, description: String, context: Context, targetActivity: Class<*>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(White, shape = RoundedCornerShape(12.dp))
            .shadow(2.dp, RoundedCornerShape(12.dp))
            .clickable {
                val intent = Intent(context, targetActivity)
                context.startActivity(intent)
            }
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "Imagen del entrenador",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(12.dp))
                .align(Alignment.CenterHorizontally)
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
            onClick = {
                val intent = Intent(context, targetActivity)
                context.startActivity(intent)
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Ver más")
        }
    }
}

@Composable
fun Footer(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.White)
    ) {
        Text(
            text = "© 2024 Gym App. Todos los derechos reservados.",
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
