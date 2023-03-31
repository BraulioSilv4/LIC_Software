import isel.leic.UsbPort
object HAL {
    var output = 0b00000000
    /**fun init() { -> por fazer
     }
     */
    fun readBits(mask : Int): Int {
        val maskBitList = decToBitList(mask)
        val usbPortBitList = decToBitList(UsbPort.read())
        val result = IntArray(8)
        for (i in maskBitList.indices) { if (maskBitList[i] == 1 && usbPortBitList[i] == 1) result[i] = 1 else result[i] = 0 }
        return bitListToDec(result.toList())
    }
    fun isBit(mask:Int): Boolean {
        val maskBitList = decToBitList(mask)
        val usbPortBitList = decToBitList(UsbPort.read())
        var isBit = false
        for (i in maskBitList.indices){ if (maskBitList[i] == 1 && usbPortBitList[i] == 1){ isBit = true } else continue }
        return isBit
    }
    fun writeBits(mask: Int,value: Int){
        val maskBitList = decToBitList(mask)
        val outputBitList = decToBitList(output).toMutableList()
        val values = decToBitList(value)
        for (i in maskBitList.indices){ if (maskBitList[i] == 1){ outputBitList[i] = values[i] } }
        output = bitListToDec(outputBitList)
        UsbPort.write(output)
    }
    fun setBits(mask: Int){
        writeBits(mask,mask)
    }
    fun clrBits(mask:Int) {
        writeBits(mask,0b00000000)
    }
}