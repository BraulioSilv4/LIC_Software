import isel.leic.UsbPort
import java.util.HexFormat
import kotlin.math.*
fun main(){
    LCD.init()
    LCD.write("A")
    Thread.sleep(2000)
    LCD.clear()
    LCD.cursor(1,4)
    LCD.write('A')
    while (true){
        val a = KBD.waitKey(1000)
        if (a != KBD.NONE){
            println(a)
        }
        else continue
    }
}