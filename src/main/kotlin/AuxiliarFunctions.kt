import kotlin.math.pow

/** Converts a decimal number into a list of integers (0s and 1s) representing the same number in binary.
 * @param n number in decimal.
 * @return number in binary.
 */
fun decToBitList(n: Int): List<Int>{
    val result = IntArray(8)
    var num = n
    var i = 0
    while (i < result.size) {
        result[i++] = num % 2
        num /= 2
    }
    return result.reversed()
}