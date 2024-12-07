package com.example.lunarloops

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import android.content.Context
import androidx.compose.runtime.Composable
import com.example.lunarloops.Planet1.World1CompleteScreen
import com.example.lunarloops.ui.LoginViewModel
import com.example.lunarloops.ui.RegisterViewModel
import com.example.lunarloops.Planet1.World1Level1
import com.example.lunarloops.Planet1.World1Level2
import com.example.lunarloops.Planet1.World1Level3
import com.example.lunarloops.Planet1.World1RulesScreen
import com.example.lunarloops.Planet2.World2CompleteScreen
import com.example.lunarloops.Planet2.World2L1Screen
import com.example.lunarloops.Planet2.World2L2Screen
import com.example.lunarloops.Planet2.World2L3Screen
import com.example.lunarloops.Planet2.World2RulesScreen

@Composable
fun Navigation(context: Context) {
    val navController = rememberNavController()
    val loginViewModel: LoginViewModel = viewModel()
    val registerViewModel: RegisterViewModel = viewModel()

    NavHost(navController = navController, startDestination = "login_screen") {

        composable("login_screen") {
            LoginScreen(loginViewModel, navController)
        }

        composable("register_screen") {
            RegisterScreen(registerViewModel, navController)
        }

        composable("home_screen"){
            HomeScreen(navController)
        }

        //Planet 1

        composable("world1_level1_screen"){
            World1Level1(navController)
        }

        composable("world1_level2_screen"){
            World1Level2(navController)
        }

        composable("world1_level3_screen"){
            World1Level3(navController)
        }

        composable("world1_rules_screen") {
            World1RulesScreen(navController)
        }

        composable("world1_complete_screen") {
            World1CompleteScreen(navController)
        }

        //Planet 2

        composable("world2_level1_screen"){
            World2L1Screen(navController)
        }

        composable("world2_level2_screen"){
            World2L2Screen(navController)
        }

        composable("world2_level3_screen"){
            World2L3Screen(navController)
        }

        composable("world2_rules_screen") {
            World2RulesScreen(navController)
        }

        composable("world2_complete_screen") {
            World2CompleteScreen(navController)
        }

    }
}