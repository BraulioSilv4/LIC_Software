import isel.leic.UsbPort
object HAL {
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
        val usbPortBitList = decToBitList(UsbPort.read()).toMutableList()
        val value = decToBitList(value)
        for (i in maskBitList.indices){ if (maskBitList[i] == 1){ usbPortBitList[i] = value[i] } }
        UsbPort.write(bitListToDec(usbPortBitList))
    }
    fun setBits(mask: Int){
        val maskBitList = decToBitList(mask)
        val usbPortBitList = decToBitList(UsbPort.read()).toMutableList()
        for (i in maskBitList.indices){ if (maskBitList[i] == 1){ usbPortBitList[i] = 1 } }
        UsbPort.write(bitListToDec(usbPortBitList))
    }
    fun clrBits(mask:Int) {
        val maskBitList = decToBitList(mask)
        val usbPortBitList = decToBitList(UsbPort.read()).toMutableList()
        for (i in maskBitList.indices){ if (maskBitList[i] == 1){ usbPortBitList[i] = 0 } }
        UsbPort.write(bitListToDec(usbPortBitList))
    }
}