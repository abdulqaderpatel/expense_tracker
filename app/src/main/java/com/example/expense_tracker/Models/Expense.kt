package com.example.expense_tracker.Models

data class Expense(
    val id:String,
    val userId:String,
    val expense:Int,
    val date:String,
    val category:String
)