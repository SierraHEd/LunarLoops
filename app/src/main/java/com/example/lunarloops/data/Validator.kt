package com.example.lunarloops.data

object Validator {

    fun validateUsername(username:String): ValidationResult{
        return ValidationResult(
            (!username.isNullOrEmpty() && username.length>=3 && username.length<=30)
        )
    }

    fun validateFirstName(fName:String): ValidationResult{
        return ValidationResult(
            (!fName.isNullOrEmpty() && fName.length>=3 && fName.length<=30)
        )
    }

    fun validateLastName(lName:String): ValidationResult{
        return ValidationResult(
            (!lName.isNullOrEmpty() && lName.length>=3 && lName.length<=30)
        )
    }


    fun validateEmail(email:String): ValidationResult{
        return ValidationResult(
            (!email.isNullOrEmpty())
        )
    }

    fun validatePassword(password:String): ValidationResult{
        return ValidationResult(
            (!password.isNullOrEmpty() && password.length>=4)
        )
    }
}

data class ValidationResult(
    val status: Boolean = false
)