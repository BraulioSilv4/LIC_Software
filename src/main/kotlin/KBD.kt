import isel.leic.utils.Time
object KBD{
    const val NONE = 0.toChar()
    private var initialized = false

    fun init() {
        if (!initialized) {
            HAL.clrBits(128)
            initialized = true
        }
    }
    fun getKey(): Char {
        if (HAL.isBit(0x80)) {
            val key = when (HAL.readBits(0x0F)){
                0x00 -> '1'
                0x01 -> '4'
                0x02 -> '7'
                0x03 -> '*'
                0x04 -> '2'
                0x05 -> '5'
                0x06 -> '8'
                0x07 -> '0'
                0x08 -> '3'
                0x09 -> '6'
                0x0A -> '9'
                0x0B -> '#'
                else -> NONE
            }
            HAL.setBits(0x80)
            while (HAL.isBit(0x80)){
                 Thread.sleep(50)
            }
            HAL.clrBits(0x80)
            return key
        }
        else return NONE
    }
    fun waitKey(timeout: Long):Char{
        val currentTime = Time.getTimeInMillis()
        while (Time.getTimeInMillis() < currentTime + timeout){
            val key = getKey()
            if (key != NONE) return key else continue
        }
        return NONE
    }
}