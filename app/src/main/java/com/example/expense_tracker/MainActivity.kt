package com.example.expense_tracker

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.navigation.compose.rememberNavController
import com.example.expense_tracker.Navigation.Graph
import com.example.expense_tracker.Navigation.RootNavGraph

import com.example.expense_tracker.ui.theme.Expense_trackerTheme
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    @SuppressLint("UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Expense_trackerTheme {

                var navController = rememberNavController()
                FirebaseAuth.getInstance().signOut()
                var isSignedIn = FirebaseAuth.getInstance().currentUser?.email != null
                RootNavGraph(
                    navController = navController,
                    startingPage = if (!isSignedIn) Graph.AUTHENTICATION else (Graph.HOME)
                )


            }
        }
    }
}

