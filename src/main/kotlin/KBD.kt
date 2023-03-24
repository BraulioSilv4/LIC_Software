import isel.leic.utils.Time

object KBD{
    const val NONE = ' '
    fun getKey(): Char {
        val kval = HAL.isBit(128)
        if (kval) {
            val key = HAL.readBits(0xF)
            return when (decToBitList(key)){
                listOf(0,0,0,0,0,0,0,0) -> '1'
                listOf(0,0,0,0,0,0,0,1) -> '4'
                listOf(0,0,0,0,0,0,1,0) -> '7'
                listOf(0,0,0,0,0,0,1,1) -> '*'
                listOf(0,0,0,0,0,1,0,0) -> '2'
                listOf(0,0,0,0,0,1,0,1) -> '5'
                listOf(0,0,0,0,0,1,1,0) -> '8'
                listOf(0,0,0,0,0,1,1,1) -> '0'
                listOf(0,0,0,0,1,0,0,0) -> '3'
                listOf(0,0,0,0,1,0,0,1) -> '6'
                listOf(0,0,0,0,1,0,1,0) -> '9'
                listOf(0,0,0,0,1,0,1,1) -> '#'
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