import java.lang.Math.pow
import java.util.ListResourceBundle
import kotlin.concurrent.thread
import kotlin.math.pow

object LCD{ // Writes to the LCD using the 4-bit interface.
    private const val LINES = 2
    private const val COLS = 16 // Dimensions of the display.

    // Writes a command/data nibble to the LCD in parallel.
     private fun writeNibbleParallel(rs: Boolean, data: Int){
        if (rs){
            HAL.setBits(0b00010000)
        }
        else HAL.clrBits(0b00010000)
        HAL.setBits(0b100000)
        HAL.writeBits(0b1111,data)
        HAL.clrBits(0b100000)
    }

    // Writes a command/data nibble to the LCD in series.
    private fun writeNibbleSerial(rs: Boolean, data: Int){
        if (rs){
            HAL.setBits(0b10000000)
        }
        else HAL.clrBits(0b10000000)
        for (i in 0 until 4){
            val mask = pow(2.0,i.toDouble()).toInt()
            HAL.writeBits(mask,data)
        }
    }

    // Writes a command/data nibble to the LCD.
    fun writeNibble(rs: Boolean, data: Int) {
        writeNibbleParallel(rs,data)
    }

    // Writes a command/data byte to the LCD.
    private fun writeByte(rs: Boolean, data: Int){
        val low = data and 0b1111
        val high = (data and 0b11110000) shr(4)
        writeNibble(rs,high)
        writeNibble(rs,low)
    }

    // Writes a command to the LCD.
    private fun writeCMD(data: Int){
        writeByte(false,data)
    }

    // Writes data to the LCD.
    private fun writeDATA(data: Int) {
        writeByte(true,data)
    }

    // Sends the initialization sequence for 4-bit communication.
    fun init() {
        Thread.sleep(100)
        writeNibble(false,0b0011)
        Thread.sleep(4,100000)
        writeNibble(false,0b0011)
        Thread.sleep(0,100000)
        writeCMD(0b00110010)
        writeCMD(0b00101000)
        writeCMD(0b00001000)
        writeCMD(0b00000001)
        writeCMD(0b00001111)
    }
    // Writes a character at the current position.
    fun write(c:Char) {
        writeDATA(c.code)
    }

    // Writes a string at the current position.
    fun write(text: String) {
        for (char in text){
            writeDATA(char.code)
        }
    }

    // Sends a command to position the cursor (‘line’:0..LINES-1 , ‘column’:0..COLS-1).
    fun cursor(line: Int, column: Int) {
       if (line == 0) {
           writeCMD(0b10000000 or column)
       }
        else writeCMD(0b10000000 or (column + 0x40))
    }

    // Sends a command to clear the screen and position the cursor at (0,0).
    fun clear(){
        writeCMD(0b00000001)
    }
}