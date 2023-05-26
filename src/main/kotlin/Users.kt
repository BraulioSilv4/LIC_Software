import java.util.StringJoiner

data class User(val uin: String, val pin: String, val name:String, var phrase:String? = null)

object Users {
    public var users1 = getUsers()
    fun getUsers():Array<User?>{
        val list = FileAccess.read("USERS.txt")
        val users = arrayOfNulls<User>(1000)
        list.forEach() {
            val info = it!!.split(";")
            users[info[0].toInt()] = User(info[0], info[1], info[2],info[3])
        }
        return users
    }
    private fun userExists(uin: String): Boolean {
        var l = 0
        var r = users1.size-1
        while (l < r) {
            val mid = l + (r - l) / 2
            if (users1[mid] == null){
                r = mid - 1
            }
            else if (users1[mid]?.uin == uin) return true
            else if (users1[mid]?.uin?.toInt()!! < uin.toInt()){
                l = mid+1
            }
            else {
                r = mid - 1
            }
        }
        return false
    }
    fun addUser(uin: String,pin:String,name:String,phrase: String? = null){
        if (userExists(uin)) {
            println("Id already exists")
        }
        else users1[uin.toInt()] = User(uin,pin,name,phrase)
        updateUsers()
    }

     fun updateUsers() {
        val listToWrite = mutableListOf<String>()
        for (user in users1) {
            if (user == null) continue
            val userWrite = "${user.uin};${user.pin};${user.name};${user.phrase ?: ""}"
            listToWrite += userWrite
        }
        FileAccess.writeData(listToWrite,"USERS.txt")
    }

    fun removeUser(uin: String){
        if (userExists(uin)){
            println("Remove user ${users1[uin.toInt()]!!.name}? Y/N")
            val answer = readln()
            if (answer == "Y") {
                users1[uin.toInt()] = null
                println("User removed successfully")
            }
        }
        else println("User doesn't exist")
    }
    fun addUserMessage(uin: String,message:String){
        if (userExists(uin)){
            users1[uin.toInt()]!!.phrase = message
        }
        else println("User does not exist.")
    }
    fun shutDown(){
        println("Are you sure? Y/N")
        val answer = readln()
        if (answer == "Y") {
            val listToWrite = mutableListOf<String>()
            for (user in users1) {
                if (user == null) continue
                val userWrite = "${user.uin};${user.pin};${user.name};${user.phrase ?: ""}"
                listToWrite += userWrite
            }
            FileAccess.writeData(listToWrite, "USERS.txt")
            TODO()
        }
        else TODO()
    }
}