package calc.jader_munoz_seccion8

object Calculator {
    fun decimalToBinary(decimal: Int): String {
        return Integer.toBinaryString(decimal)
    }

    fun binaryToDecimal(binary: String): Int {
        return Integer.parseInt(binary, 2)
    }

    fun add(a: Int, b: Int): Int {
        return a + b
    }

    fun subtract(a: Int, b: Int): Int {
        return a - b
    }
}