var closed = false
data class User(val ID: Int, var pin: Int, val name:String, var phrase:String? = null)
const val KEYPRESS_MAX_WAIT_TIME = 5000.toLong()
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