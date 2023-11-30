package com.example.expense_tracker.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.expense_tracker.Main.UserNavigation

@Composable
fun RootNavGraph(navController: NavHostController, startingPage: String) {
    NavHost(navController = navController, route = Graph.ROOT, startDestination = startingPage)
    {
        authNavGraph(navController=navController)

        composable(route = Graph.HOME)
        {
            UserNavigation()
        }
    }
}


object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val HOME = "home_graph"
    const val DETAILS = "details_graph"
}