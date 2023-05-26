
object Users {
    public var userList = getUsers()
    fun getUsers():Array<User?>{
        val list = FileAccess.read("USERS.txt")
        val users = arrayOfNulls<User>(1000)
        list.forEach() {
            val info = it!!.split(";")
            users[info[0].toInt()] = User(info[0].toInt(), info[1].toInt(), info[2],info[3])
        }
        return users
    }
    fun userAuthentic(id:Int, pin:Int):Boolean {
        println("User : $id, pin: $pin")
        return userList[id]!!.pin == pin
    }

     fun userExists(id: Int): Boolean {
         for (user in userList){
             if (user == null) continue
             else if (user.ID == id) return true
         }
         return false
        var l = 0
        var r = userList.size-1
        while (l < r) {
            val mid = l + (r - l) / 2
            if (userList[mid] == null){
                r = mid - 1
            }
            else if (userList[mid]?.ID == id) {
                println("yes")
                return true
            }
            else if (userList[mid]?.ID!! < id){
                l = mid+1
            }
            else {
                r = mid - 1
            }
        }
        return false
    }
    fun addUser(id: Int,pin:Int,name:String,phrase: String? = null){
        if (userExists(id)) {
            println("ID already exists")
        }
        else {
            userList[id] = User(id,pin,name,phrase)
            println("Added user : ${userList[id]!!.name} with the ID : $id")
        }
    }

     fun updateUsers() {
        val listToWrite = mutableListOf<String>()
        for (user in userList) {
            if (user == null) continue
            val userWrite = "${user.ID};${user.pin};${user.name};${user.phrase ?: ""}"
            listToWrite += userWrite
        }
        FileAccess.writeData(listToWrite,"USERS.txt")
    }

    fun removeUser(id: Int){
        if (userExists(id)){
            println("Remove user ${userList[id]!!.name}? Y/N")
            val answer = readln()[0].uppercaseChar()
            if (answer == 'Y') {
                userList[id] = null
                println("User removed successfully")
            }
        }
        else println("User doesn't exist")
    }

    fun addUserMessage(id: Int, message:String){
        if (userExists(id)){
            userList[id]!!.phrase = message
        }
        else println("User does not exist.")
    }

}