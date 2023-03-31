import isel.leic.UsbPort
import kotlin.math.*
fun main(){
    HAL.setBits(0b10100000)
    val a = readln()
    HAL.setBits(0b10100000)
    val b = readln()
    HAL.setBits(0b1111)
}