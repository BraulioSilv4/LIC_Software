object LCD{ // Writes to the LCD using the 4-bit interface.
    private const val LINES = 2
    private const val COLS = 16 // Dimensions of the display.
    // Writes a command/data nibble to the LCD in parallel
    private fun writeNibbleParallel(rs: Boolean, data: Int){
        HAL.writeBits(0b1111,data)
    }
    // Writes a command/data nibble to the LCD in series
    private fun writeNibbleSerial(rs: Boolean, data: Int){

    }
    // Writes a command/data nibble to the LCD
    private fun writeNibble(rs: Boolean, data: Int) {
        writeNibbleParallel(rs,data)
    }
    // Writes a command/data byte to the LCD
    private fun writeByte(rs: Boolean, data: Int){

    }
    // Writes a command to the LCD
    private fun writeCMD(data: Int){

    }
    // Writes data to the LCD
    private fun writeDATA(data: Int) {

    }
    // Sends the initialization sequence for 4-bit communication.
    fun init() {

    }
    // Writes a character at the current position.
    fun write(c: Char){

    }
    // Writes a string at the current position.
    fun write(text: String) {

    }
    // Sends a command to position the cursor (‘line’:0..LINES-1 , ‘column’:0..COLS-1)
    fun cursor(line: Int, column: Int){

    }
    // Sends a command to clear the screen and position the cursor at (0,0)
    fun clear(){

    }
}