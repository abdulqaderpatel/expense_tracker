package com.example.expense_tracker.Navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoGraph
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.expense_tracker.Authentication.Login
import com.example.expense_tracker.Authentication.Signup
import com.example.expense_tracker.Main.Home
import com.example.expense_tracker.Main.Insights
import com.example.expense_tracker.Main.UserNavigation

@Composable
fun MainNavGraph(modifier: Modifier=Modifier,navController: NavHostController)
{
    NavHost(navController = navController, route = Graph.HOME, startDestination =BottomBarScreen.Home.route )
    {
        composable(route=BottomBarScreen.Home.route)
        {
            Home(navController=navController)
        }
        composable(route = BottomBarScreen.Insight.route)
        {
            Insights()
        }
        composable(route = Graph.AUTHENTICATION) {
            Login(navController = navController)
        }

    }
}


sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomBarScreen(
        route = "HOME",
        title = "HOME",
        icon = Icons.Default.Home
    )

    object Insight : BottomBarScreen(
        route = "INSIGHT",
        title = "INSIGHT",
        icon = Icons.Default.AutoGraph
    )


}