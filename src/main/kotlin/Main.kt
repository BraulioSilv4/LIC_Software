import isel.leic.UsbPort
import kotlin.math.*
fun main(){
    while (true){
        val letter = KBD.getKey()
        if (letter != KBD.NONE){
            println(letter)
        }
        else continue
    }
}