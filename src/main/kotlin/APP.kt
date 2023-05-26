import java.util.concurrent.TimeUnit
import javax.swing.Timer

object APP {
    val speed = 5
    fun runAPP(){
        while (!HAL.isBit(0b00100000)){
            authenticate()
        }
        if (!closed) Maintenance.maint()
    }

    private fun getUser(): User? {
        val uin = TUI.getUIN()
        if (uin == null) getUser()
        val pin = TUI.getPIN()
        if (pin == null) {
            LCD.write("Couldn't read pin. Try again.")
            TimeUnit.SECONDS.sleep(2)
            TUI.getPIN()
        }
        if (uin == null || pin == null) getUser()
        else if(Users.userExists(uin) && Users.userAuthentic(uin,pin)) {
            return Users.users1[uin.toInt()]
        }
        return null
    }
    private fun authenticate(){
        val user =getUser()
        if (user != null ){
            LCD.clear()
            if (user.phrase != null) {
                LCD.write(user.phrase!!)
                val waitForKey = KBD.waitKey(5000)
                if (waitForKey == '#') removeMessage(user)
            }
            else{
                LCD.write("Authentication complete.")
                Log.newRegistration(true,user)
            }
            openDoor()
            if (KBD.waitKey(5000) == '#'){
                changePIN(user)
            }
        }
    }

    private fun changePIN(user: User) {
        LCD.clear()
        LCD.write("Insert new PIN: ")

        var pin = "" ; var secondTry = ""

        repeat(PIN_SIZE * 2) {
            val key = KBD.waitKey(KEYPRESS_MAX_WAIT_TIME)
            if (key != KBD.NONE) {
                if (pin.length < PIN_SIZE) pin += key else secondTry += key
            } else {
                LCD.clear()
                LCD.write("Timed Out.")
                TimeUnit.SECONDS.sleep(2)
                return
            }
        }

        if (pin == secondTry) {
            user.pin = pin
            LCD.write("PIN of user ${user.uin} has been changed.")
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