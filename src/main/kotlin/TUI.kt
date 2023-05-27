object TUI {


    /** Attempts to read a uID from the hardware for 5 seconds.
     * @return given uID or null if the attempt to read was unsuccessful.
     */
    fun getUIN(): Int? {
        LCD.clear()
        LCD.write("Insert UIN:???")
        LCD.cursor(0,11)
        var uin = ""

        repeat(UIN_SIZE) {
            val key = KBD.waitKey(5000)
            if(key != KBD.NONE){
                LCD.write(key)
                uin += key
            }
            else {
                LCD.clear()
                LCD.write("Timed Out.")
                Thread.sleep(2000)
                LCD.clear()
                return null
            }
        }
        LCD.clear()
        return uin.toInt()
    }


    /** Attempts to read a PIN from the hardware for 5 seconds.
     * @return given PIN or null if the attempt to read was unsuccessful.
     */
     fun getPIN(message:String): Int? {
        LCD.clear()
        LCD.write(message)
        var pin = ""

        repeat(PIN_SIZE) {
            val key = KBD.waitKey(5000)
            if (key != KBD.NONE) {
                LCD.write('*')
                pin += key
            } else {
                LCD.clear()
                LCD.write("Timed Out.")
                Thread.sleep(2000)
                return null
            }
        }
        return pin.toInt()
    }
}