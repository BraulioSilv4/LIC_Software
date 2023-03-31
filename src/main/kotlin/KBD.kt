 import isel.leic.utils.Time

object KBD{
    const val NONE = 0.toChar()
    fun getKey(): Char {
        val kval = HAL.isBit(0b10000000)
        if (kval) {
            return when (HAL.readBits(0b01111)){
                0b0000 -> '1'
                0b0001 -> '4'
                0b0010 -> '7'
                0b0011 -> '*'
                0b0100 -> '2'
                0b0101 -> '5'
                0b0110 -> '8'
                0b0111 -> '0'
                0b01000 -> '3'
                0b01001 -> '6'
                0b01010 -> '9'
                0b01011 -> '#'
                else -> NONE
            }
        }
        else return NONE
    }
    fun waitKey(timeout: Long):Char{
        val currentTime = Time.getTimeInMillis()
        while (Time.getTimeInMillis() < currentTime + timeout){
            val a = getKey()
            if (a != NONE) return a else continue
        }
        return NONE
    }
}