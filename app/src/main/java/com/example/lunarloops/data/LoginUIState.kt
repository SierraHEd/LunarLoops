package com.example.lunarloops.data

data class LoginUIState(
    var username  :String = "",
    var password  :String = "",

    var usernameError :Boolean = false,
    var passwordError : Boolean = false

)