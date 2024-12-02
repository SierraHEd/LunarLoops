package com.example.lunarloops.ui

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.lunarloops.data.RegistrationUIState
import com.example.lunarloops.data.UIEvent
import com.example.lunarloops.data.Validator

class RegisterViewModel : ViewModel(){
    private val TAG = RegisterViewModel::class.simpleName
    var registrationUIState = mutableStateOf(RegistrationUIState())
    var allValidationsPassed = mutableStateOf(false)


    fun onEvent(event: UIEvent, navController: NavController){
        validateDataWithRules()
        when(event){
            is UIEvent.UsernameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(username = event.username)
                printState()
            }
            is UIEvent.FirstNameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(firstName = event.firstName)
                printState()
            }
            is UIEvent.LastNameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(lastName = event.lastName)
                printState()
            }
            is UIEvent.EmailChanged-> {
                registrationUIState.value = registrationUIState.value.copy(email = event.email)
                printState()
            }
            is UIEvent.PasswordChanged -> {
                registrationUIState.value = registrationUIState.value.copy(password = event.password)
                printState()
            }
            is UIEvent.RegisterButtonClicked -> {
                signUp(navController)
            }
        }
    }

    private  fun signUp(navController: NavController){
        Log.d(TAG, "Inside_signUp")
        printState()

    }

    private fun validateDataWithRules(){
        val userNameResult = Validator.validateUsername(
            username = registrationUIState.value.username
        )

        val fNameResult = Validator.validateFirstName(
            fName = registrationUIState.value.firstName
        )

        val lNameResult = Validator.validateLastName(
            lName = registrationUIState.value.lastName
        )


        val emailResult = Validator.validateEmail(
            email = registrationUIState.value.email
        )

        val passwordResult = Validator.validatePassword(
            password = registrationUIState.value.password
        )

        Log.d(TAG, "Inside_validateDateWithRules")
        Log.d(TAG, "fNameResult= $fNameResult")
        Log.d(TAG, "lNameResult= $lNameResult")
        Log.d(TAG, "emailResult= $emailResult")
        Log.d(TAG, "passwordResult = $passwordResult")

        registrationUIState.value = registrationUIState.value.copy(
            usernameError = userNameResult.status,
            firstNameError = fNameResult.status,
            lastNameError = lNameResult.status,
            emailError = emailResult.status,
            passwordError = passwordResult.status
        )

        if(userNameResult.status && fNameResult.status && lNameResult.status && emailResult.status && passwordResult.status)
            allValidationsPassed.value = true
        else
            allValidationsPassed.value = false
    }

    private fun printState(){
        Log.d(TAG, "Inside_printState")
        Log.d(TAG, registrationUIState.value.toString())
    }



}