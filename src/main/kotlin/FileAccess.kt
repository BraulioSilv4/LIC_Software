import java.io.BufferedReader
import java.io.FileReader

object FileAccess{
    fun read(file:String): List<String?>{
        val reader = createReader(file)
        val data = mutableListOf<String>()
        reader.forEachLine {line ->
            data.add(line)
        }
        println(data)
        return data.toList()
    }
    fun writeData(data:List<String?>, file:String){
        val writer = createWriter(file)
        data.forEach{
            writer.println(it)
        }
        writer.close()
    }
}