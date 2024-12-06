package com.example.lunarloops.data

sealed class UIEvent{
    data class UsernameChanged(val username:String) : UIEvent()
    data class FirstNameChanged(val firstName:String) : UIEvent()
    data class LastNameChanged(val lastName: String) : UIEvent()
    data class EmailChanged(val email:String) : UIEvent()
    data class PasswordChanged(val password:String) : UIEvent()
    data class ChildNameChanged(val childname:String) : UIEvent()
    data class AgeChanged(val age: String) : UIEvent()

    object RegisterButtonClicked : UIEvent()
}