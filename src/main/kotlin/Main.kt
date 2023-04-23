import isel.leic.UsbPort
fun main(){
    LCD.init()
    var counter = 0
    var a = 0
    val b = "ABC"
    var line = 0
    var col = 0
    while (true){
        while (col < 14){
            LCD.clear()
            LCD.cursor(line,col)
            LCD.write(b)
            Thread.sleep(150)
            col++
        }
        col--
        while (col > 0){
            LCD.clear()
            LCD.cursor(line,col)
            LCD.write(b)
            Thread.sleep(150)
            col--
        }
    }
}