import isel.leic.utils.Time
import kotlin.concurrent.thread

object KBD{
    const val NONE = 0.toChar()
    fun getKey(): Char {
        if (HAL.isBit(0b10000000)) {
            val key = when (HAL.readBits(0b1111)){
                0b0000 -> '1'
                0b0001 -> '4'
                0b0010 -> '7'
                0b0011 -> '*'
                0b0100 -> '2'
                0b0101 -> '5'
                0b0110 -> '8'
                0b0111 -> '0'
                0b1000 -> '3'
                0b1001 -> '6'
                0b1010 -> '9'
                0b1011 -> '#'
                else -> NONE
            }
            HAL.setBits(128)
            while (HAL.isBit(128)){
                 Thread.sleep(50)
            }
            HAL.clrBits(128)
            return key
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