import java.util.concurrent.TimeUnit

object SerialEmmiter { // Envia tramas para os diferentes módulos Serial Receiver.
    private var inititialized = false
    fun init() {
        if (!inititialized){
            HAL.setBits(0b1100)
            inititialized = true
        }
    }
    enum class Destination {LCD, DOOR}
    fun send(addr: Destination, data: Int) {
        val data = decToBitList(data)
        var count = 0
        val maxClk = 5
        while (count < maxClk){
            if (addr == Destination.LCD) HAL.clrBits(0x04)
            else if (addr == Destination.DOOR) HAL.clrBits(0x08)
            TimeUnit.NANOSECONDS.sleep(40)
            HAL.clrBits(0b10)
            val dataWrite = data[data.size-1 - count]
            HAL.writeBits(0b001,dataWrite)
            TimeUnit.NANOSECONDS.sleep(40)
            HAL.setBits(0b10)
            count++
        }
        HAL.clrBits(0b010)
        if (addr == Destination.LCD) HAL.setBits(0x04) else HAL.setBits(0x08)
    }
    fun isBusy():Boolean = !HAL.isBit(0x40)
}