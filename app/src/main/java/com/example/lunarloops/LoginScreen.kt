package com.example.lunarloops

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.lunarloops.data.AppStorage
import com.example.lunarloops.data.ButtonComponent
import com.example.lunarloops.data.HeadingTextComponent
import com.example.lunarloops.data.LoginUIEvent
import com.example.lunarloops.data.MyTextField
import com.example.lunarloops.data.NormalTextComponent
import com.example.lunarloops.data.PasswordTextField
import com.example.lunarloops.data.RegisterComponent
import com.example.lunarloops.ui.AppPreferences
import com.example.lunarloops.ui.LoginViewModel
import com.example.lunarloops.ui.theme.Space

@Composable
fun LoginScreen(loginViewModel: LoginViewModel = viewModel(), navController: NavController){
    val store = AppStorage(LocalContext.current)
    val appPrefs = store.appPreferencesFlow.collectAsStateWithLifecycle(AppPreferences())
    val imageBrush =
        ShaderBrush(ImageShader(ImageBitmap.imageResource(id = R.drawable.starcbck)))

    Surface(
        color = Space,
        modifier = Modifier
            .fillMaxSize()
            .background(imageBrush)
            .padding(28.dp)
    ){
        Column(modifier = Modifier.fillMaxSize().background(imageBrush)) {
            //ImageComponent()
            HeadingTextComponent("Login")

            Spacer(modifier = Modifier.height(20.dp))

            MyTextField(
                labelValue = "Username",
                painterIcon = rememberVectorPainter(Icons.Default.AccountCircle),
                onTextSelected = {
                    loginViewModel.onEvent(LoginUIEvent.UsernameChanged(it), navController)
                },
                loginViewModel.loginUIState.value.usernameError

            )
            Spacer(modifier = Modifier.height(20.dp))

            PasswordTextField(
                labelValue = "Password",
                painterIcon = rememberVectorPainter(Icons.Default.Lock),
                onTextSelected = {
                    loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it), navController)
                },
                loginViewModel.loginUIState.value.passwordError

            )

            Spacer(modifier = Modifier.height(60.dp))

            ButtonComponent("Login", onButtonClick = {
                loginViewModel.onEvent(LoginUIEvent.onLoginButtonClicked, navController)
                if(appPrefs.value.username.contentEquals(loginViewModel.loginUIState.value.username) && appPrefs.value.password.contentEquals(loginViewModel.loginUIState.value.password)){
                    navController.navigate("home_screen")
                }
            },
                isEnabled = loginViewModel.allValidationsPassed.value)

            Spacer(modifier = Modifier.height(30.dp))

            NormalTextComponent("Don't Have an Account?")

            Spacer(modifier = Modifier.height(20.dp))

            RegisterComponent("Register", navController)
        }

    }

}