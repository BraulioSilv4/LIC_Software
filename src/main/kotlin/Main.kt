import isel.leic.UsbPort
import java.util.HexFormat
import kotlin.math.*
fun main(){
    while (true){
        val a = KBD.waitKey(1000)
        if (a != KBD.NONE){
            println(a)
        }
        else continue
    }
}