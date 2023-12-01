package com.example.expense_tracker.Models

data class Expense(
    val id:String,
    val userId:String,
    val title:String,
    val expense: Any,
    val date:String,
    val category:String
)