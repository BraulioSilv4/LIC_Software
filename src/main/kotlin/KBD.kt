import isel.leic.utils.Time
object KBD{
    //masks for signals
    private const val DATA = 0xF
    private const val DVAL = 0x80
    private const val ACK = 0x80

    const val NONE = 0.toChar()
    private var initialized = false

    fun init() {
        if (!initialized) {
            HAL.clrBits(128)
            initialized = true
        }
    }
    /** Attempts to read a key from the hardware
    * @return Char representing the key or NONE if no key is available */
    fun getKey(): Char {
        if (HAL.isBit(DVAL)) {
            val key = when (HAL.readBits(DATA)){
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
            HAL.setBits(ACK)
            while (HAL.isBit(DVAL)){
                Thread.sleep(50)
            }
            HAL.clrBits(ACK)
            return key
        }
        else return NONE
    }

    /** Attempts to read a key for a set period of time.
     * @param timeout time to read a key in milliseconds.
     * @return Char representing the key or NONE if no key is made available during the set time.*/
    fun waitKey(timeout: Long):Char{
        val currentTime = Time.getTimeInMillis()
        while (Time.getTimeInMillis() < currentTime + timeout){
            val key = getKey()
            if (key != NONE) return key else continue
        }
        return NONE
    }
}