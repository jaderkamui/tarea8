package calc.jader_munoz_seccion8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import calc.jader_munoz_seccion8.ui.components.CalculatorButton
import calc.jader_munoz_seccion8.ui.components.CalculatorDisplay
import calc.jader_munoz_seccion8.ui.theme.JaderMunozSeccion8Theme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JaderMunozSeccion8Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CalculatorScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorScreen(viewModel: CalculatorViewModel = viewModel()) {
    val isDecimalMode by viewModel.isDecimalMode
    val input = if (isDecimalMode) viewModel.decimalInput else viewModel.binaryInput
    val result = if (isDecimalMode) viewModel.decimalResult else viewModel.binaryResult
    val display = viewModel.display

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Button(onClick = { viewModel.toggleMode() }) {
                Text(text = if (isDecimalMode) "Modo Binario" else "Modo Decimal")
            }
            Spacer(modifier = Modifier.padding(10.dp))
            CalculatorDisplay(text = display)
            CalculatorDisplay(text = input)
            CalculatorDisplay(text = result)
            Spacer(modifier = Modifier.padding(10.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    CalculatorButton(text = "7", onClick = { viewModel.onNumberClick("7") })
                    CalculatorButton(text = "8", onClick = { viewModel.onNumberClick("8") })
                    CalculatorButton(text = "9", onClick = { viewModel.onNumberClick("9") })
                    CalculatorButton(
                        text = if (isDecimalMode) "+" else "+",
                        onClick = { if (isDecimalMode) viewModel.addDecimalNumbers() else viewModel.addBinaryNumbers() }
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    CalculatorButton(text = "4", onClick = { viewModel.onNumberClick("4") })
                    CalculatorButton(text = "5", onClick = { viewModel.onNumberClick("5") })
                    CalculatorButton(text = "6", onClick = { viewModel.onNumberClick("6") })
                    CalculatorButton(
                        text = if (isDecimalMode) "-" else "-",
                        onClick = { if (isDecimalMode) viewModel.subtractDecimalNumbers() else viewModel.subtractBinaryNumbers() }
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    CalculatorButton(text = "1", onClick = { viewModel.onNumberClick("1") })
                    CalculatorButton(text = "2", onClick = { viewModel.onNumberClick("2") })
                    CalculatorButton(text = "3", onClick = { viewModel.onNumberClick("3") })
                    CalculatorButton(text = "=", onClick = { viewModel.onEqualsClick() })
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    CalculatorButton(text = "0", onClick = { viewModel.onNumberClick("0") })
                    CalculatorButton(text = "Limpiar", onClick = { viewModel.clear() }, color = Color.Red)
                    if (isDecimalMode) {
                        CalculatorButton(text = "Convertir a Binario", onClick = { viewModel.toggleMode() })
                    } else {
                        CalculatorButton(text = "Convertir a Decimal", onClick = { viewModel.toggleMode() })
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorScreenPreview() {
    JaderMunozSeccion8Theme {
        CalculatorScreen()
    }
}