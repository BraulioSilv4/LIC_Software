import isel.leic.UsbPort

fun main(){
    while(true){
        println(HAL.readBits(0x80))
    }
}

