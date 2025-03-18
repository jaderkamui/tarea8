package calc.jader_munoz_seccion8

class Calculator {
    fun decimalToBinary(decimal: Int): String {
        return Integer.toBinaryString(decimal)
    }

    fun binaryToDecimal(binary: String): Int {
        return binary.toInt(2)
    }

    fun addDecimal(num1: Int, num2: Int): Int {
        return num1 + num2
    }

    fun subtractDecimal(num1: Int, num2: Int): Int {
        return num1 - num2
    }

    fun addBinary(binary1: String, binary2: String): String {
        val decimal1 = binaryToDecimal(binary1)
        val decimal2 = binaryToDecimal(binary2)
        val sum = decimal1 + decimal2
        return decimalToBinary(sum)
    }

    fun subtractBinary(binary1: String, binary2: String): String {
        val decimal1 = binaryToDecimal(binary1)
        val decimal2 = binaryToDecimal(binary2)
        val difference = decimal1 - decimal2
        return decimalToBinary(difference)
    }
}