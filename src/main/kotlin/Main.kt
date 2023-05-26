var closed = false
data class User(val uin: String, var pin: String, val name:String, var phrase:String? = null)
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
    Thread.sleep(2000)
    APP.runAPP()
}

fun shutDown(){
    println("Are you sure? Y/N")
    val answer = readln()
    if (answer == "Y") {
      Users.updateUsers()
      closed = true
    }
}