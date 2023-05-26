var closed = false
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
}

fun shutDown(){
    println("Are you sure? Y/N")
    val answer = readln()
    if (answer == "Y") {
      Users.updateUsers()
      closed = true
    }
}