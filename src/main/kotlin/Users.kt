data class User(val uin: String, val pin: String, val phrase:String? = null)

object Users {
    fun getUsers(){
        val list = FileAccess.read("USERS.txt")
        val users = mutableListOf<User>()
        list.forEach(){
            val info = it!!.split(";")

        }
    }
    fun acessAllowed(): Boolean = accessAccepted(TUI.getUIN(), TUI.getPIN(),  TODO() )

    private fun accessAccepted(uin: String?, pin: String?, users: Array<User>): Boolean {
        for (i in users.indices) {
            val user = users[i]
            if (user.uin == uin)
                return user.pin == pin
        }
        return false
    }

}