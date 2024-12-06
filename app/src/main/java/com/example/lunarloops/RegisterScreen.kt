package com.example.lunarloops

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.lunarloops.data.AppStorage
import com.example.lunarloops.data.ButtonComponent
import com.example.lunarloops.data.LoginComponent
import com.example.lunarloops.data.MyTextField
import com.example.lunarloops.data.NormalTextComponent
import com.example.lunarloops.data.PasswordTextField
import com.example.lunarloops.data.UIEvent
import com.example.lunarloops.ui.RegisterViewModel
import kotlinx.coroutines.launch

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@Composable
fun RegisterScreen(registerViewModel: RegisterViewModel = viewModel(), navController: NavController) {
    val store = AppStorage(LocalContext.current)
    val coroutineScope = rememberCoroutineScope()

    Surface(
        color = Color.Blue,
        modifier = Modifier.fillMaxSize()
            .background(Color.Blue)
            .padding(28.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            //ImageComponent()
            Spacer(modifier = Modifier.height(20.dp))

            MyTextField(
                labelValue = "Username",
                painterIcon = rememberVectorPainter(Icons.Default.AccountCircle),
                onTextSelected = {
                    registerViewModel.onEvent(UIEvent.UsernameChanged(it), navController)
                },
                registerViewModel.registrationUIState.value.usernameError
            )

            MyTextField(
                labelValue = "First Name",
                painterIcon = rememberVectorPainter(Icons.Default.Create),
                onTextSelected = {
                    registerViewModel.onEvent(UIEvent.FirstNameChanged(it), navController)
                },
                registerViewModel.registrationUIState.value.firstNameError
            )

            MyTextField(
                labelValue = "Last Name",
                painterIcon = rememberVectorPainter(Icons.Default.Create),
                onTextSelected = {
                    registerViewModel.onEvent(UIEvent.LastNameChanged(it), navController)
                },
                registerViewModel.registrationUIState.value.lastNameError
            )

            MyTextField(
                labelValue = "Email",
                painterIcon = rememberVectorPainter(Icons.Default.Email),
                onTextSelected = {
                    registerViewModel.onEvent(UIEvent.EmailChanged(it), navController)
                },
                registerViewModel.registrationUIState.value.emailError
            )
            PasswordTextField(
                labelValue = "Password",
                painterIcon = rememberVectorPainter(Icons.Default.Lock),
                onTextSelected = {
                    registerViewModel.onEvent(UIEvent.PasswordChanged(it), navController)
                },
                registerViewModel.registrationUIState.value.passwordError
            )

            MyTextField(
                labelValue = "Child Name",
                painterIcon = rememberVectorPainter(Icons.Default.AccountCircle),
                onTextSelected = {
                    registerViewModel.onEvent(UIEvent.ChildNameChanged(it), navController)
                },
                registerViewModel.registrationUIState.value.cnameError
            )

            MyTextField(
                labelValue = "Child Age",
                painterIcon = rememberVectorPainter(Icons.Default.DateRange),
                onTextSelected = {
                    registerViewModel.onEvent(UIEvent.ChildNameChanged(it), navController)
                },
                registerViewModel.registrationUIState.value.ageError
            )

            Spacer(modifier = Modifier.height(40.dp))

            ButtonComponent(
                "Register", onButtonClick = {
                    registerViewModel.onEvent(UIEvent.RegisterButtonClicked, navController)
                    coroutineScope.launch {
                        store.saveUsername(registerViewModel.registrationUIState.value.username)
                        store.savePassword(registerViewModel.registrationUIState.value.password)
                    }
                    navController.navigate("login_screen")
                },
                isEnabled = registerViewModel.allValidationsPassed.value
            )

            Spacer(modifier = Modifier.height(20.dp))

            NormalTextComponent("Already Have and Account?")

            Spacer(modifier = Modifier.height(10.dp))

            LoginComponent("Login", navController)


        }

    }


}