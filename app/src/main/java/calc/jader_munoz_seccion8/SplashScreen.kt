package calc.jader_munoz_seccion8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import calc.jader_munoz_seccion8.ui.theme.JaderMunozSeccion8Theme

class SplashScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JaderMunozSeccion8Theme {
                SplashContent()
            }
        }
    }
}

@Composable
fun SplashContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Bienvenido a la Calculadora",
            style = MaterialTheme.typography.headlineMedium.copy(fontSize = 30.sp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashContentPreview() {
    JaderMunozSeccion8Theme {
        SplashContent()
    }
}