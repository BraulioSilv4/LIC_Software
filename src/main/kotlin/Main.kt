import java.util.concurrent.TimeUnit

fun main() {
    HAL.init()
    KBD.init()
    SerialEmmiter.init()
    LCD.init()
    DoorMechanism.init()
    DoorMechanism.open(0b0001)
    FileAcess.getUsers()
    FileAcess.addUser(User(1,2,"d"))
    FileAcess.writeUsers()
    TimeUnit.SECONDS.sleep(10)
    DoorMechanism.close(0b0001)
    while (true) {
        TimeUnit.SECONDS.sleep(1)
        println(DoorMechanism.finished())
    }
}