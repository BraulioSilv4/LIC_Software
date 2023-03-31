import isel.leic.UsbPort
import kotlin.math.*
fun main(){
    var queque = false
    while (true){
        val a = KBD.waitKey(100)
        if (a != KBD.NONE){
            println(a)
            queque = true
        }
        if (queque){
            HAL.setBits(128)
        }
        else HAL.clrBits(128)
    }
}

