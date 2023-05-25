object TUI {
    private const val UIN_SIZE = 3
    private const val PIN_SIZE = 4

    fun getUIN(): String? {
        LCD.clear()
        LCD.write("Insert UIN: ")
        var uin = ""

        repeat(UIN_SIZE) {
            val key = KBD.waitKey(5000)
            if(key != KBD.NONE){
                uin += key
            }
            else {
                LCD.clear()
                LCD.write("Timed Out.")
                Thread.sleep(2000)
                return null
            }
        }
        LCD.clear()
        return uin
    }

     fun getPIN(): String? {
        LCD.clear()
        LCD.write("Insert PIN: ")
        var pin = ""

        repeat(PIN_SIZE) {
            val key = KBD.waitKey(5000)
            if (key != KBD.NONE) {
                pin += key
            } else {
                LCD.clear()
                LCD.write("Timed Out.")
                Thread.sleep(2000)
                return null
            }
        }
        return pin
    }
}