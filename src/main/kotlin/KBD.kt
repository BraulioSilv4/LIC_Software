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
                0x0 -> '1'
                0x1 -> '4'
                0x2 -> '7'
                0x3 -> '*'
                0x4 -> '2'
                0x5 -> '5'
                0x6 -> '8'
                0x7 -> '0'
                0x8 -> '3'
                0x9 -> '6'
                0xA -> '9'
                0xB -> '#'
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