import kotlin.system.exitProcess

object Maintenance {
    fun maint(){
        LCD.clear()
        LCD.write("Out of Service")
        while (HAL.isBit(0x20) && !closed){
            println("Please select the desired option")
            println("1 - Add user")
            println("2 - Remove a user")
            println("3 - Add a user message")
            println("4 - Shutdown the system")
            when (readln().toInt()){
                1 -> addUser()
                2 -> removeUser()
                3 -> insertMessage()
                4 -> shutDown()
            }
        }
        if (!closed) APP.runAPP()
    }
    private fun addUser() {
        var availableId = -1
        for (id in Users.userList.indices){
            if (Users.userList[id] == null){
                 availableId = id
                break
            }
        }
        if (availableId == -1) println("No Ids available")
        else{
            println("Select PIN (4 numbers)")
            val answer = readln().toInt()
            if (answer.toString().length > 4) {
                println("PIN too large.")
                return
            }
            println("Insert name:")
            val name = readln()
            Users.addUser(availableId,answer,name)
        }
    }

    private fun removeUser(){
        println("Insert the uID of the user to remove(3 numbers)")
        val answer = readln().toInt()
        if (answer.toString().length > 3){
            println("PIN too large.")
            return
        }
        Users.removeUser(answer)
    }

    private fun insertMessage(){
        println("Insert the uID of the user to insert message(3 numbers)")
        val answer = readln().toInt()
        if (answer.toString().length > 3){
            println("UID too large.")
            return
        }
        println("Insert the desired message:")
        val message = readln()
        Users.addUserMessage(answer,message)
    }

    private fun shutDown(){
        println("Are you sure you want to shutdown the system? Y/N")
        val answer = readln()[0].uppercaseChar()
        if (answer == 'Y') {
            Users.updateUsers()
            exitProcess(0)
        }
        else return
    }

}