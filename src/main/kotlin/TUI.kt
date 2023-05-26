const val UIN_SIZE = 3
const val PIN_SIZE = 4

object TUI {

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
        println(uin.toInt())
        return uin.toInt()
    }

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
         println(pin.toInt())
        return pin.toInt()
    }
}