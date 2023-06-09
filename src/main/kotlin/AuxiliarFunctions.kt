import java.io.BufferedReader
import java.io.FileReader
import java.io.PrintWriter
import java.lang.NumberFormatException

/** Creates a reader which allows to read data from a given file
 * @param fileName name of the file
 */
fun createReader(fileName: String): BufferedReader {
    return BufferedReader(FileReader(fileName))
}


/** Creates a writer which allows to store data on a given file.
 * @param fileName name of the file.
 */
fun createWriter(fileName: String): PrintWriter {
    return PrintWriter(fileName)
}

fun readInt():Int{
    return try {
        readln().toInt()
    }
    catch (e:NumberFormatException ){
        print("Invalid number. Try again: ")
        readInt()
    }
}