package cs.smu.ac.embeddedsoftware

data class UserData(
    var username: String? = "",
    var email: String? = "",
    var password: String? = "",
    var studentNumber: Int? = null,
    var phoneNumber: String? = ""
)