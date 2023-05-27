import java.util.concurrent.TimeUnit
object APP {
    //velocity to open the door (value from 0 to 15)
    val speed = 0

    /** Runs the system on access mode while the maintenance bit is set to '0' .*/
    fun runAPP(){
        while (!HAL.isBit(MBIT)){ authenticate() }
        Maintenance.maint()
    }


    /** Asks and reads a user from the hardware.
     * @return user if the read was successful or null if not.*/
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


    /** Reads a user and if its valid allows access by logging the access,opening the door. */
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


    /** Allows for a change of user pin asking for confirmation.
     * @param user to change the pin
     */
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


    // Opens the door for the user to enter and closes it
    private fun openDoor(){
        DoorMechanism.open(speed)
        while (!DoorMechanism.finished()){
            Thread.sleep(100)
        }
        DoorMechanism.close(speed)
    }


    /** Removes the displayed user message from the system.
     * @param user which the message is associated to.
     */
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