import java.io.BufferedReader
import java.io.FileReader
import java.io.PrintWriter

/** Converts a decimal number into a list of integers (0s and 1s) representing the same number in binary.
 * @param n number in decimal.
* @return number in binary.
*/
fun decToBitList(n: Int): List<Int>{
    val result = IntArray(5)
    var num = n
    var i = 0
    while (i < result.size) {
        result[i++] = num % 2
        num /= 2
    }
    return result.reversed()
}

fun createReader(fileName: String): BufferedReader {
    return BufferedReader(FileReader(fileName))
}

fun createWriter(fileName: String?): PrintWriter {
    return PrintWriter(fileName)
}