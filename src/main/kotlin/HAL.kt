import isel.leic.UsbPort
object HAL {
    var currentOutput = 0
    private var initialized = false
    fun init() {
        if (!initialized) {
            clrBits(0xFF)
            initialized = true
        }
    }
    fun readBits(mask : Int): Int {
        return UsbPort.read() and mask
    }
    fun isBit(mask:Int): Boolean {
        return (readBits(mask) != 0)
    }
    fun writeBits(mask: Int,value: Int){
        currentOutput = (mask and value) or (currentOutput and mask.inv())
        UsbPort.write(currentOutput)
    }
    fun setBits(mask: Int){
        writeBits(mask,mask)
    }
    fun clrBits(mask:Int) {
        writeBits(mask,0)
    }
}