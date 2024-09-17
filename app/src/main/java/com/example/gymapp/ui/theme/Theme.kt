import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = RedLight,      // Rojo claro como color primario en modo oscuro
    secondary = RedDark,    // Rojo oscuro como color secundario en modo oscuro
    tertiary = DarkGray     // Gris oscuro para detalles en modo oscuro
)

private val LightColorScheme = lightColorScheme(
    primary = Red,          // Rojo intenso como color primario en modo claro
    secondary = Black,     // Negro como color secundario en modo claro
    tertiary = WhiteGray,  // Gris muy claro para detalles en modo claro
    background = White,    // Blanco como fondo en modo claro
    surface = White,       // Blanco como superficie en modo claro
    onPrimary = White,     // Texto blanco sobre el rojo
    onSecondary = White,   // Texto blanco sobre el negro
    onBackground = Black, // Texto negro sobre el fondo blanco
    onSurface = Black     // Texto negro sobre superficies blancas
)

@Composable
fun GymAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography, // Usa la tipografía personalizada aquí
        content = content
    )
}
