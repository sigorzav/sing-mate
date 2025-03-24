package com.sigorzav.singmate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sigorzav.singmate.ui.navigation.Routes
import com.sigorzav.singmate.ui.user.SignInScreen
import com.sigorzav.singmate.ui.user.SignUpScreen
import com.sigorzav.singmate.viewmodel.user.SignInViewModel
import com.sigorzav.singmate.viewmodel.user.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()
            val signInViewModel: SignInViewModel = hiltViewModel()
            val signUpViewModel: SignUpViewModel = hiltViewModel()

            NavHost(navController = navController, startDestination = Routes.SIGN_IN) {
                composable(Routes.SIGN_IN) {
                    SignInScreen(
                        viewModel = signInViewModel,
                        onSignUpClick = { navController.navigate(Routes.SIGN_UP) }
                    )
                }
                composable(Routes.SIGN_UP) {
                    SignUpScreen(
                        viewModel = signUpViewModel,
                        onSignInClick = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}