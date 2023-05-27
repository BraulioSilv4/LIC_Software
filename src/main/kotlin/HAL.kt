import isel.leic.UsbPort
object HAL {
    var currentOutput = 0
    private var initialized = false

    //Initializes the class by clearing every bit in the UsbPort.
    fun init() {
        if (!initialized) {
            clrBits(0xFF)
            initialized = true
        }
    }

    /**Reads the value of the bits indicated by the mask in the UsbPort.
     * @param mask of the bits to read
     * @return value of the masked bits in the UsbPort.*/
    fun readBits(mask : Int): Int {
        return UsbPort.read() and mask
    }

    /** Checks if a given bit is set to '1' in the UsbPort.
     * @param mask bit to check
     * @return true if the bit is set to '1' or false otherwise.
     */
    fun isBit(mask:Int): Boolean {
        return (readBits(mask) == mask)
    }

    /** Updates the output with a given value to the bits indicated by the mask
     * @param mask bits to alter the value to.
     * @param value to alter the bits to.*/
    fun writeBits(mask: Int,value: Int){
        currentOutput = (mask and value) or (currentOutput and mask.inv())
        UsbPort.write(currentOutput)
    }

    /** Sets the given bits to '1'
     * @param mask of the bits to set.*/
    fun setBits(mask: Int){
        writeBits(mask,mask)
    }

    /** Sets the given bits to '0'
     * @param mask of the bits to clear.
     */
    fun clrBits(mask:Int) {
        writeBits(mask,0)
    }
}