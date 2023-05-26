import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Log {
    var logs = FileAccess.read("LogFile.txt").toMutableList()
    fun newRegistration(accessAllowed: Boolean, user: User) {
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")
        val currentTime = LocalDateTime.now().format(formatter)

        logs += accessTriedReg(accessAllowed, user, currentTime)

        FileAccess.writeData(logs, "LogFile.txt")
    }
    private fun accessTriedReg(accessAllowed: Boolean, user: User, time: String) =
        if (accessAllowed)
            "$time - The user ${user.uin} accessed the system."
        else
            "$time - Unknown user tried to access the system!"
}
