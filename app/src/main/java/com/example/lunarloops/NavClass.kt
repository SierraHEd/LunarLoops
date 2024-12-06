package com.example.lunarloops

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lunarloops.ui.LoginViewModel
import com.example.lunarloops.ui.RegisterViewModel
import com.example.lunarloops.ui.World1Level1
import com.example.lunarloops.ui.World1Level2

@Composable
fun Navigation(context: Context) {
    val navController = rememberNavController()
    val loginViewModel: LoginViewModel = viewModel()
    val registerViewModel: RegisterViewModel = viewModel()

    NavHost(navController = navController, startDestination = "world1_level1_screen") {

        composable("login_screen") {
            LoginScreen(loginViewModel, navController)
        }

        composable("register_screen") {
            RegisterScreen(registerViewModel, navController)
        }

        composable("world1_level1_screen"){
            World1Level1(navController)
        }
        composable("world1_level2_screen"){
            World1Level2(navController)
        }

    }
}