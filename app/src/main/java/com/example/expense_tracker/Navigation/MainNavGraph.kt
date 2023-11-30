package com.example.expense_tracker.Navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoGraph
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.expense_tracker.Main.Home
import com.example.expense_tracker.Main.Insights

@Composable
fun MainNavGraph(navController: NavHostController)
{
    NavHost(navController = navController, route = Graph.HOME, startDestination =BottomBarScreen.Home.route )
    {
        composable(route=BottomBarScreen.Home.route)
        {
            Home()
        }
        composable(route = BottomBarScreen.Insight.route)
        {
            Insights()
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