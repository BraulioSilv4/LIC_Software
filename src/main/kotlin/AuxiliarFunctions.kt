import kotlin.math.pow

/** Converts a decimal number into a list of integers (0s and 1s) representing the same number in binary.
 * @param n number in decimal.
 * @return number in binary.
 */
fun decToBitList(n: Int): List<Int>{
    val result = IntArray(8)
    var num = n
    var i = 0
    while (n > 0 && i < result.size) {
        result[i++] = num % 2
        num /= 2
    }
    return result.reversed()
}
/** converts a binary number represented in a list of integers (0s and 1s) into the corresponding decimal number.
 * @param num list of integers representing a number in binary.
 * @return number in decimal.
 */
fun bitListToDec(num: List<Int>): Int {
    var result = 0
    for (i in num.indices){ result += num[i]*(2.0).pow(7-i).toInt() }
    return result
}