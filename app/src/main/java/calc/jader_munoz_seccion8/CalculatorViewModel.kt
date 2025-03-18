package calc.jader_munoz_seccion8

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State

class CalculatorViewModel : ViewModel() {
    private val calculator = Calculator()

    private val _isDecimalMode = mutableStateOf(true)
    val isDecimalMode: State<Boolean> = _isDecimalMode

    private val _decimalInput = mutableStateOf("")
    val decimalInput: String get() = _decimalInput.value

    private val _decimalResult = mutableStateOf("")
    val decimalResult: String get() = _decimalResult.value

    private val _binaryInput = mutableStateOf("")
    val binaryInput: String get() = _binaryInput.value

    private val _binaryResult = mutableStateOf("")
    val binaryResult: String get() = _binaryResult.value

    private val _display = mutableStateOf("")
    val display: String get() = _display.value

    private var lastOperation: Operation? = null
    private var lastNumber: String = ""

    fun onDecimalInputChange(input: String) {
        _decimalInput.value = input
    }

    fun onBinaryInputChange(input: String) {
        _binaryInput.value = input
    }

    fun convertDecimalToBinary() {
        try {
            val decimal = _decimalResult.value.toInt()
            _binaryResult.value = calculator.decimalToBinary(decimal)
        } catch (e: NumberFormatException) {
            _binaryResult.value = "Error"
        }
    }

    fun convertBinaryToDecimal() {
        try {
            val binary = _binaryResult.value
            _decimalResult.value = calculator.binaryToDecimal(binary).toString()
        } catch (e: NumberFormatException) {
            _decimalResult.value = "Error"
        }
    }

    fun addDecimalNumbers() {
        performOperation(Operation.ADD)
    }

    fun subtractDecimalNumbers() {
        performOperation(Operation.SUBTRACT)
    }

    fun addBinaryNumbers() {
        performOperation(Operation.ADD)
    }

    fun subtractBinaryNumbers() {
        performOperation(Operation.SUBTRACT)
    }

    private fun performOperation(operation: Operation) {
        val currentInput = if (_isDecimalMode.value) _decimalInput.value else _binaryInput.value
        if (currentInput.isEmpty()) return

        if (lastOperation != null && lastNumber.isNotEmpty()) {
            calculateResult()
        }
        lastOperation = operation
        lastNumber = currentInput
        _display.value = "$lastNumber ${operation.symbol}"
        clearInput()
    }

    private fun calculateResult() {
        if (lastOperation == null || lastNumber.isEmpty()) return

        val currentInput = if (_isDecimalMode.value) _decimalInput.value else _binaryInput.value
        if (currentInput.isEmpty()) return

        try {
            val result: Any = when (lastOperation) {
                Operation.ADD -> {
                    if (_isDecimalMode.value) {
                        calculator.addDecimal(lastNumber.toInt(), currentInput.toInt())
                    } else {
                        calculator.addBinary(lastNumber, currentInput)
                    }
                }
                Operation.SUBTRACT -> {
                    if (_isDecimalMode.value) {
                        calculator.subtractDecimal(lastNumber.toInt(), currentInput.toInt())
                    } else {
                        calculator.subtractBinary(lastNumber, currentInput)
                    }
                }
                else -> throw IllegalStateException("Operación no válida")
            }
            if (_isDecimalMode.value) {
                _decimalResult.value = result.toString()
            } else {
                _binaryResult.value = result.toString()
            }
            _display.value = "$lastNumber ${lastOperation?.symbol} $currentInput ="
        } catch (e: NumberFormatException) {
            if (_isDecimalMode.value) {
                _decimalResult.value = "Error"
            } else {
                _binaryResult.value = "Error"
            }
        }

        lastOperation = null
        lastNumber = ""
        clearInput()
    }

    fun onEqualsClick() {
        calculateResult()
    }

    fun toggleMode() {
        if (_isDecimalMode.value) {
            if (_decimalResult.value.isNotEmpty() && _decimalResult.value != "Error") {
                convertDecimalToBinary()
            }
        } else {
            if (_binaryResult.value.isNotEmpty() && _binaryResult.value != "Error") {
                convertBinaryToDecimal()
            }
        }
        _isDecimalMode.value = !_isDecimalMode.value
    }

    fun clear() {
        _decimalInput.value = ""
        _binaryInput.value = ""
        _decimalResult.value = ""
        _binaryResult.value = ""
        _display.value = ""
        lastOperation = null
        lastNumber = ""
    }

    private fun clearInput() {
        if (_isDecimalMode.value) {
            _decimalInput.value = ""
        } else {
            _binaryInput.value = ""
        }
    }

    fun onNumberClick(number: String) {
        if (_isDecimalMode.value) {
            _decimalInput.value += number
        } else {
            _binaryInput.value += number
        }
    }

    enum class Operation(val symbol: String) {
        ADD("+"), SUBTRACT("-")
    }
}