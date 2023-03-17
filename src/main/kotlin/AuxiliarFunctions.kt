import isel.leic.UsbPort
import kotlin.math.*

fun readBits(mask: Int): Int{
    val maskBinary = Integer.toBinaryString(mask)
    val usbRead = Integer.toBinaryString(UsbPort.read())
    var res = 0
    for(i in maskBinary.indices){
        if(maskBinary[i]=='1' && i < usbRead.length){
            res += ((usbRead[i].digitToInt())*(2.toDouble().pow(i))).toInt()
            //println("i:$i usbRead:${usbRead[i]} maskBinary;${maskBinary[i]}, maskBinaryLenght: ${maskBinary.length} usbReadLenght. ${usbRead.length}")
        }
    }
    return res
}