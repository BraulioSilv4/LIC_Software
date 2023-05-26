import java.util.concurrent.TimeUnit

object APP {
    val speed = 5
    fun runAPP(){
        while (!HAL.isBit(0b00100000)){
            authenticate()
        }
        if (!closed) Maintenance.maint()
    }

    private fun getUser(): User? {
        var uID = TUI.getUIN()
        if (uID == null) uID = TUI.getUIN()
        var pin = TUI.getPIN("Insert PIN:")
        if (pin == null) {
            TimeUnit.SECONDS.sleep(2)
            pin = TUI.getPIN("Insert PIN:")
        }
        if (uID == null || pin == null) getUser()
        else if(Users.userExists(uID) && Users.userAuthentic(uID,pin)) {
            return Users.userList[uID.toInt()]
        }
        return null
    }
    private fun authenticate(){
        val user =getUser()
        if (user != null ){
            Log.newRegistration(true,user)
            LCD.clear()
            if (user.phrase != null) {
                LCD.write(user.phrase!!)
                val waitForKey = KBD.waitKey(5000)
                if (waitForKey == '#') removeMessage(user)
            }
            else{
                LCD.write("Authentication complete.")
            }
            openDoor()
            if (KBD.waitKey(5000) == '#'){
                changePIN(user)
            }
        }
    }

    private fun changePIN(user: User) {
        LCD.clear()
        val pin = TUI.getPIN("new PIN:")
        val pin2 = TUI.getPIN("Repeat:")
        if (pin == pin2) {
            LCD.clear()
            user.pin = pin!!
            LCD.write("PIN of user ${user.ID} has been changed.")
        } else {
            LCD.clear()
            LCD.write("PINs don't match!")
        }

        TimeUnit.SECONDS.sleep(3)
        LCD.clear()
    }
    private fun openDoor(){
        DoorMechanism.open(speed)
        while (!DoorMechanism.finished()){
            Thread.sleep(100)
        }
        DoorMechanism.close(speed)
    }
    private fun removeMessage(user: User){
        LCD.clear()
        LCD.write("Removing message... ")

        if (user.phrase != null) {
            user.phrase = null
            TimeUnit.SECONDS.sleep(3)
            LCD.clear()
            LCD.write("Message removed successfully!")
        }
        TimeUnit.SECONDS.sleep(2)
        LCD.clear()
    }
}