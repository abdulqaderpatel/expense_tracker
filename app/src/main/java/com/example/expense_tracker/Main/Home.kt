package com.example.expense_tracker.Main

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.google.firebase.auth.FirebaseAuth

@Composable
fun Home() {

    Text(text = FirebaseAuth.getInstance().currentUser!!.displayName.toString())
}