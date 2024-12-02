package com.example.lunarloops.data

sealed class LoginUIEvent{
    data class UsernameChanged(val username:String) : LoginUIEvent()
    data class PasswordChanged(val password:String) : LoginUIEvent()

    object onLoginButtonClicked: LoginUIEvent()
}