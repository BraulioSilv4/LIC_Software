import isel.leic.UsbPort

object HAL {
    fun readBits(mask : Int): Int {
        val maskBitList = decToBitList(mask)
        val usbPortBitList = decToBitList(UsbPort.read())
        var result = IntArray(8)
        for (i in maskBitList.indices) {
            if (maskBitList[i] == 1 && usbPortBitList[i] == 1) result[i] = 1 else result[i] = 0
        }
        return bitListToDec(result.toList())
    }
    fun isBit(mask:Int): Boolean {
        return (decToBitList(mask).contains(1))
    }
    fun writeBits(mask: Int,value: Int){
        val maskBitList = decToBitList(mask)
        var usbPortBitList = decToBitList(UsbPort.read()).toMutableList()
        for (i in maskBitList.indices){
            if (maskBitList[i] == 1){ usbPortBitList[i] = value }
        }
        UsbPort.write(bitListToDec(usbPortBitList))
    }
    fun setBits(mask: Int){
        writeBits(mask, 1)
    }
    fun clrBits(mask:Int) {
        writeBits(mask,0)
    }
}