
object FileAccess{
    fun read(file:String): List<String?>{
        val reader = createReader(file)
        var line: String? = reader.readLine()
        var data = mutableListOf(line)
        while (line != null){
            data.add(line)
            line = readLine()
        }
        return data
    }
    fun writeData(data:String, file:String){
        val writer = createWriter(file)
        val currentData = read(file).toMutableList()
        currentData.add(data)
        currentData.forEach{
            writer.println(it)
        }
        writer.close()
    }
}