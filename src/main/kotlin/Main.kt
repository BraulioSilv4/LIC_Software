const val MBIT = 0x20
const val UIN_SIZE = 3
const val PIN_SIZE = 4

data class User(val ID: Int, var pin: Int, val name:String, var phrase:String? = null)



// Initalizes every class associated with the hardware.
fun init(){
    print("Initializing System...")
    HAL.init()
    KBD.init()
    SerialEmmiter.init()
    LCD.init()
    DoorMechanism.init()
    println("\rSystem Initialized.")
}

fun main() {
   init()
    APP.runAPP()
}