import java.util.ServiceConfigurationError
import java.util.concurrent.TimeUnit

object SerialEmmiter { // Envia tramas para os diferentes módulos Serial Receiver.

    private var inititialized = false

    //masks for signals
    private const val SClk = 0x2
    private const val SDX = 0x1
    private const val LCDSEL = 0x4
    private const val DCSEL = 0x8
    private const val Busy = 0x40

    /** Initializes the class by putting both nSS signals with the value '1' and putting the clk signal generated from this class with '0'*/
    fun init() {
        if (!inititialized){
            HAL.setBits(LCDSEL or DCSEL)
            HAL.clrBits(SClk)
            inititialized = true
        }
    }
    enum class Destination {LCD, DOOR}
    /** Sends data in series to the specified destination.
     * @param addr destination of the data to send.
     * @param data information to send.
     */
    fun send(addr: Destination, data: Int) {
        if (addr == Destination.LCD) HAL.clrBits(LCDSEL) else HAL.clrBits(DCSEL)
        for (count in 0 until 5)  {
            Thread.sleep(5)
            HAL.clrBits(SClk)
            val dataWrite = data shr(count) and SDX
            HAL.writeBits(SDX,dataWrite)
            Thread.sleep(5)
            HAL.setBits(SClk)
        }
        HAL.clrBits(2)
        if (addr == Destination.LCD) HAL.setBits(LCDSEL) else HAL.setBits(DCSEL)
    }
    /** Checks if the SDC is busy
     * @return true if the module is busy and false if it's free.
     */
    fun isBusy():Boolean = !HAL.isBit(Busy)
}