import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.concurrent.thread
public var hour = ""
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
    val main = thread{ APP.runAPP() }
    println(hour.toString())
    val hours = thread {
        while (true){
            val currentDateTime = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
            hour = currentDateTime.format(formatter)
        }
    }
}