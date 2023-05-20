import java.util.concurrent.TimeUnit

data class User(val Id: Int, val password: Int, val name : String, val phrase: String? = null)

object FileAccess{
    val users = arrayOfNulls<User>(1000)

    fun getUsers(){
        val reader = createReader("USERS.txt")
        var line: String? = reader.readLine()
        var i = 0
        while (line != null){
            val data = line.split(';')
            users[i] = User(data[0].toInt(),data[1].toInt(),data[2],data[3])
            line = reader.readLine()
            i++
        }
        println(users.toList())
    }

    fun writeUsers(){
        val writer = createWriter("USERS.txt")
        var i = 0
        while (users[i] != null){
            writer.println("${users[i]?.Id};${users[i]?.password};${users[i]?.name};${users[i]?.phrase?: ""}")
            i++
        }
        writer.close()
    }

    fun addUser(user: User){
        var i = 0
        while (users[i] != null){
            if(users[i]?.Id == user.Id) {
                LCD.write("ID already exists.")
                TimeUnit.SECONDS.sleep(2)
                LCD.clear()
                return
            }
            i++
        }
        users[i] = user
    }
}