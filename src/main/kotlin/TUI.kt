object TUI {

    /** Attempts to read a uID from the hardware for 5 seconds.
     * @return given uID or null if the attempt to read was unsuccessful. */
    fun getUIN(): Int? {

        LCD.clear()
        LCD.write(hour+"Insert UIN:???")
        LCD.cursor(1,11)
        var uin = ""

        repeat(UIN_SIZE) {
            val key = KBD.waitKey(5000)
            if (key == '*'){
                if (uin.isEmpty()) return null
                return getUIN()
            }
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
        return uin.toIntOrNull()
    }


    /** Attempts to read a PIN from the hardware for 5 seconds.
     * @return given PIN or null if the attempt to read was unsuccessful. */
     fun getPIN(message:String): Int? {
        LCD.clear()
        LCD.write(hour+message)
        var pin = ""

        repeat(PIN_SIZE) {
            val key = KBD.waitKey(5000)
            if (key == '*'){
                if (pin.isEmpty()) return null
                return getPIN(message)
            }
            else if (key != KBD.NONE) {
                LCD.write('*')
                pin += key
            } else {
                LCD.clear()
                LCD.write("Timed Out.")
                Thread.sleep(2000)
                return null
            }
        }
        return pin.toIntOrNull()
    }
}