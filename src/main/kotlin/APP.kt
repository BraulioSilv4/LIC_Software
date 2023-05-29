import java.util.concurrent.TimeUnit
object APP {
    //velocity to open the door (value from 0 to 15)
    val speed = 0

    /** Runs the system on access mode while the maintenance bit is set to '0' .*/
    fun runAPP(){
        while (!HAL.isBit(MBIT)){ login() }
        Maintenance.maintenance()
    }
    /** Asks and reads a user from the hardware.
     * @return user if the read was successful or null if not.*/
    private fun getUser(): User? {
        if (HAL.isBit(MBIT)) return null
        val uID = TUI.getUIN() ?: return getUser()
        val pin = TUI.getPIN("Insert PIN:") ?: return getUser()
        if(Users.userExists(uID) && Users.userAuthentic(uID, pin)) return Users.userList[uID]
        else if (!Users.userExists(uID)){
            Log.newRegistration(false, null)
            LCD.clear()
            LCD.write("No user found!")
            TimeUnit.SECONDS.sleep(2)
        }
        else if( !Users.userAuthentic(uID, pin)){
            LCD.clear()
            LCD.write("PIN mismatch!")
            TimeUnit.SECONDS.sleep(2)
        }
        return null
    }


    /** Reads a user and if its valid allows access by logging the access,opening the door. */
    private fun login(){
        if (HAL.isBit(MBIT)) return
        val user =getUser()
        if (user != null ){
            Log.newRegistration(true,user)
            LCD.clear()
            if (user.phrase != null) {
                LCD.write("${user.name}: ${user.phrase}")
                openDoor(user)
                LCD.clear()
            }
            else {
                LCD.write(user.name)
                openDoor(user)
                LCD.clear()
            }
            if (HAL.isBit(MBIT)) return
            LCD.write("Change PIN? (#)")
            if (KBD.waitKey(5000) == '#'){
                changePIN(user)
            }
        }

    }


    /** Allows for a change of user pin asking for confirmation.
     * @param user to change the pin
     */
    private fun changePIN(user: User) {
        if (HAL.isBit(MBIT)) return
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


    // Opens the door for the user to enter and closes it, also allows for removing the displayed message from the system.
    private fun openDoor(user: User){
        if(HAL.isBit(MBIT)) return
        DoorMechanism.open(speed)
        while (!DoorMechanism.finished()){
            if (HAL.isBit(MBIT)) return
            if (KBD.getKey() == '*' && user.phrase != null) removeMessage(user)
            Thread.sleep(100)
        }
        DoorMechanism.close(speed)
        while (!DoorMechanism.finished()){
            if (HAL.isBit(MBIT)) return
            if (KBD.getKey() == '*' && user.phrase != null) removeMessage(user)
        }
    }


    /** Removes the displayed user message from the system.
     * @param user which the message is associated to.
     */
    private fun removeMessage(user: User){
        if (HAL.isBit(MBIT)) return
        LCD.clear()
        LCD.write("Removing message... ")
        Users.removeUserMessage(user.ID)
        TimeUnit.SECONDS.sleep(2)
        LCD.clear()
        LCD.write("Message removed successfully!")
        TimeUnit.SECONDS.sleep(2)
        LCD.clear()
    }

}