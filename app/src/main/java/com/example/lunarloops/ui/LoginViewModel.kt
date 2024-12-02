package com.example.lunarloops.ui

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.lunarloops.data.LoginUIEvent
import com.example.lunarloops.data.LoginUIState
import com.example.lunarloops.data.Validator

class LoginViewModel : ViewModel() {

    private val TAG = LoginViewModel::class.simpleName

    var loginUIState = mutableStateOf(LoginUIState())

    var allValidationsPassed = mutableStateOf(false)

    var loginInProgress = mutableStateOf(false)

    fun onEvent(event: LoginUIEvent, navController: NavController) {
        when (event) {
            is LoginUIEvent.UsernameChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    username = event.username
                )
            }

            is LoginUIEvent.PasswordChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    password = event.password
                )
            }

            is LoginUIEvent.onLoginButtonClicked -> {
                login(navController)

            }
        }
        validateLoginUIDataWithRules()
    }

    private fun login(navController: NavController) {
        Log.d(TAG, "Inside_login")

    }

    private fun validateLoginUIDataWithRules() {
        val usernameResult = Validator.validateUsername(
            username = loginUIState.value.username
        )


        val passwordResult = Validator.validatePassword(
            password = loginUIState.value.password
        )

        loginUIState.value = loginUIState.value.copy(
            usernameError = usernameResult.status,
            passwordError = passwordResult.status
        )

        allValidationsPassed.value = usernameResult.status && passwordResult.status

    }




}