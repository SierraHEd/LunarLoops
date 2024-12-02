package com.example.lunarloops

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lunarloops.planetDD.DDLevel1Screen
import com.example.lunarloops.ui.LoginViewModel
import com.example.lunarloops.ui.RegisterViewModel

@Composable
fun Navigation(context: Context) {
    val navController = rememberNavController()
    val loginViewModel: LoginViewModel = viewModel()
    val registerViewModel: RegisterViewModel = viewModel()

    NavHost(navController = navController, startDestination = "drag_and_drop_level1") {

        composable("login_screen") {
            LoginScreen(loginViewModel, navController)
        }

        composable("register_screen") {
            RegisterScreen(registerViewModel, navController)
        }

        composable("drag_and_drop_level1"){
            DDLevel1Screen(navController)
        }


    }
}