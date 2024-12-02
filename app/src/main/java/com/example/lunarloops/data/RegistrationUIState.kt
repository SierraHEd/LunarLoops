package com.example.lunarloops.data

data class RegistrationUIState(
    var username: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var password: String = "",

    var usernameError: Boolean = false,
    var firstNameError: Boolean = false,
    var lastNameError: Boolean = false,
    var emailError: Boolean = false,
    var passwordError: Boolean = false

)