import java.io.Serial

object DoorMechanism {
    var initialized = false
    // Initializes the class, establishing initial values.
    fun init() {
        if (!initialized){
            initialized = true
        }
    }
    // Sends a command to open the door with the given velocity parameter
    fun open(velocity: Int) {
        val data = velocity shl(1) or 1
        SerialEmmiter.send(SerialEmmiter.Destination.DOOR,data)
    }
    // Sends a command to close the door with the given velocity parameter
    fun close(velocity: Int) {
        val data = velocity shl(1)
        SerialEmmiter.send(SerialEmmiter.Destination.DOOR,data)
    }
    // Checks if the previous command has finished
    fun finished() : Boolean = SerialEmmiter.isBusy()
}