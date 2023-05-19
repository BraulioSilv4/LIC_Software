import java.util.concurrent.TimeUnit

object SerialEmmiter { // Envia tramas para os diferentes módulos Serial Receiver.
    enum class Destination {LCD, DOOR}
    fun send(addr: Destination, data: Int) {
        val data = decToBitList(data)
        var count = 0
        val maxClk = 5
        while (count < maxClk){
            TimeUnit.NANOSECONDS.sleep(20)
            val dataWrite = 0b01 + data[count]
            HAL.writeBits(0b111,dataWrite)
            TimeUnit.NANOSECONDS.sleep(20)
            HAL.clrBits(0b10)
            count++
        }
        HAL.setBits(0b100)
    }

    fun isBusy(): Boolean =
}