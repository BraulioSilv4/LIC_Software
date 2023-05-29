
object DoorMechanism {
    var initialized = false

    // Initializes the class, establishing initial values.
    fun init() {
        if (!initialized){
            close(15)
            initialized = true
        }
    }

    /** Sends a command to open the door.
     * @param velocity speed at which the door will open (value between 0 and 15).*/
    fun open(velocity: Int) {
        val data = velocity shl(1) or 1
        SerialEmmiter.send(SerialEmmiter.Destination.DOOR,data)
    }

    /** Sends a command to close the door
     * @param velocity speed at which the door will close (value between 0 and 15) */
    fun close(velocity: Int) {
        val data = velocity shl(1)
        SerialEmmiter.send(SerialEmmiter.Destination.DOOR,data)
    }

    // Checks if the previous command has finished
    fun finished() : Boolean = SerialEmmiter.isBusy()
}