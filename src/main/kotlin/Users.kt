
object Users {
    var userList = getUsers()

    /** Gets the users from the files and stores them
     * @return Array of size 1000 with the users and nulls on spaces where users don't exist yet.
     */
    private fun getUsers():Array<User?>{
        val list = FileAccess.read("USERS.txt")
        val users = arrayOfNulls<User>(1000)
        list.forEach() {
            val info = it!!.split(";")
            users[info[0].toInt()] = User(info[0].toInt(), info[1].toInt(), info[2],info[3])
        }
        return users
    }


    // Updates the user file with the info on the user array.
    fun updateUsers() {
        val listToWrite = mutableListOf<String>()
        for (user in userList) {
            if (user == null) continue
            val userWrite = "${user.ID};${user.pin};${user.name};${user.phrase ?: ""}"
            listToWrite += userWrite
        }
        FileAccess.writeData(listToWrite,"USERS.txt")
    }


    /** Checks if a uID exists.
     * @param id to check
     * @return true if the id exists or false if it doesn't.
     */
     fun userExists(id: Int): Boolean {
         for (user in userList){
             if (user == null) continue
             else if (user.ID == id) return true
         }
         return false
    }


    /** Checks if a given id-pin pair exists in the system.
     * @param id of the user.
     * @param pin to check association with the uID.
     * @return true if the pair is valid, false if it isn't.*/
    fun userAuthentic(id:Int, pin:Int):Boolean = userList[id]!!.pin == pin


    /** Adds a user to the user array if the given id is available.
     * @param id of the user.
     * @param pin for the user to access the system.
     * @param name of the user.
     * @param phrase associated to the user if none is given it is set to null*/
    fun addUser(id: Int,pin:Int,name:String,phrase: String? = null){
        if (userExists(id)) {
            println("ID already exists!")
        }
        else {
            userList[id] = User(id,pin,name,phrase)
            println("Added user : ${userList[id]!!.name} with the ID: $id")
        }
    }

    /** Removes a user from the user array after asking for confirmation.
     * @param id of the user to remove.*/
    fun removeUser(id: Int){
        if (userExists(id)){
            println("Remove user ${userList[id]!!.name}? Y/N")
            val answer = readln()[0].uppercaseChar()
            if (answer == 'Y') {
                userList[id] = null
                println("User removed successfully.")
            }
        }
        else println("User does not exist.")
    }

    /** Adds/replaces a message to a given user if it exists on the user array.
     * @param id of the user
     * @param message to associate to the user.
     */
    fun addUserMessage(id: Int, message:String?){
        if (userExists(id)){
            userList[id]!!.phrase = message
        }
        else println("User does not exist.")
    }

    /** Removes a message associated to a given user
     * @param id of the user to remove the message*/
    fun removeUserMessage(id: Int){
        addUserMessage(id,null)
    }

}