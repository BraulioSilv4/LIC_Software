import kotlin.system.exitProcess

object Maintenance {
    // Puts the system on maintenance mode while the maintenance signal is set to '1'.
    fun maintenance(){
        println("Mode: Maintenance")
        LCD.clear()
        LCD.write("Out of Service")
        while (HAL.isBit(MBIT)){
            println("Please select the desired option:")
            println("1 - Add user")
            println("2 - Remove a user")
            println("3 - Add a user message")
            println("4 - Shutdown the system")
            when (readInt()){
                1 -> addUser()
                2 -> removeUser()
                3 -> insertMessage()
                4 -> shutDown()
            }
        }
        println("Mode: Access")
        APP.runAPP()
    }

    //Adds a user to the system
    private fun addUser() {
        var availableId = -1
        for (id in Users.userList.indices){
            if (Users.userList[id] == null){
                availableId = id
                break
            }
        }
        if (availableId == -1) println("No IDs available")
        else{
            println("Select PIN (max 4 numbers)")
            val answer = readInt()
            if (answer.toString().length > 4) {
                println("PIN too large.")
                return
            }
            println("Insert name:")
            val name = readln()
            Users.addUser(availableId,answer,name)
        }
    }

    //Removes a user from the system
    private fun removeUser(){
        println("Insert the uID of the user to remove (max 3 numbers)")
        val answer = readInt()
        if (answer.toString().length > 3){
            println("uID too large.")
            return
        }
        Users.removeUser(answer)
    }

    // Adds a message to a user given by the client.
    private fun insertMessage(){
        println("Insert the uID of the user to insert message(3 numbers)")
        val answer = readInt()
        if (answer.toString().length > 3){
            println("UID too large.")
            return
        }
        println("Insert the desired message:")
        val message = readln()
        Users.addUserMessage(answer,message)
    }

    // Shuts down the system upon receiving confirmation.
    private fun shutDown() {
        println("Are you sure you want to shutdown the system? Y/N")
        val answer = readln()[0].uppercaseChar()
        if (answer == 'Y') {
            println("Shutting Down...")
            Users.updateUsers()
            exitProcess(0)
        }
        else return
    }
}