package com.sigorzav.singmate

import SignInScreen
import SignUpScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sigorzav.singmate.viewmodel.auth.AuthViewModel

class AuthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()
            val viewModel: AuthViewModel = viewModel()

            NavHost(navController = navController, startDestination = "login") {
                composable("login") {
                    SignInScreen(
                        viewModel = viewModel,
                        onSignUpClick = { navController.navigate("signup") }
                    )
                }
                composable("signup") {
                    SignUpScreen(
                        viewModel = viewModel,
                        onSignInClick = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}