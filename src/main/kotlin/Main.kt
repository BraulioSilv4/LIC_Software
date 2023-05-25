
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
    LCD.write('A')
}