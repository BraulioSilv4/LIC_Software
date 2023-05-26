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
        for (user in Users.users1.indices){
            if (Users.users1[user] == null){
                 availableId = user
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
            Users.addUser(availableId.toString(),answer.toString(),name)
        }
    }

    private fun removeUser(){
        println("Insert the uID of the user to remove(3 numbers)")
        val answer = readln().toInt()
        if (answer.toString().length > 3){
            println("PIN too large.")
            return
        }
        Users.removeUser(answer.toString())
    }

    private fun insertMessage(){
        println("Insert the uID of the user to remove(3 numbers)")
        val answer = readln().toInt()
        if (answer.toString().length > 3){
            println("PIN too large.")
            return
        }
        println("Insert the desired message:")
        val message = readln()
        Users.addUserMessage(answer.toString(),message)
    }

    private fun shutDown(){
        println("Are you sure you want to shutdown the system? Y/N")
        val answer = readln()[0].uppercaseChar()
        if (answer == 'Y') {
            println(Users.users1.toList())
            Users.updateUsers()
            closed = true
        }
        else return
    }
}