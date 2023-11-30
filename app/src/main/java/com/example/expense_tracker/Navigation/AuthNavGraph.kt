package com.example.expense_tracker.Navigation

import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.expense_tracker.Authentication.EmailVerification
import com.example.expense_tracker.Authentication.Login
import com.example.expense_tracker.Authentication.Signup

fun NavGraphBuilder.authNavGraph(navController: NavHostController)
{
    navigation(route=Graph.AUTHENTICATION, startDestination = AuthScreen.Login.route)
    {
        composable(route=AuthScreen.Login.route)
        {
            Login(navController = navController)
        }
        composable(route=AuthScreen.SignUp.route)
        {
           Signup(navController = navController)
        }
        composable(route=AuthScreen.EmailVerify.route)
        {
            EmailVerification(navController = navController)
        }

    }
}




sealed class AuthScreen(val route: String) {
    object Login : AuthScreen(route = "LOGIN")
    object SignUp : AuthScreen(route = "SIGN_UP")
    object EmailVerify : AuthScreen(route = "VERIFY_EMAIL")
}