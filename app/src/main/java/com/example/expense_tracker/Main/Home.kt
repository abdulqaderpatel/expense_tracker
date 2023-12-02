package com.example.expense_tracker.Main

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.expense_tracker.Models.Expense
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home() {

    val expenses = mutableStateListOf<Expense>()

    var totalAmount by remember {
        mutableLongStateOf(0)
    }

    var selectedTimeList = listOf<String>("Today", "Last 7 Days", "This Month")
    var selectedTime by remember {
        mutableStateOf("Today")
    }

    var today = LocalDate.now().format(DateTimeFormatter.ofPattern("d MMMM", Locale.ENGLISH))

    var dailyDate = LocalDate.now()






    Scaffold(topBar = {
        TopAppBar(title = {
            Column {
                Text(
                    text = "Good Morning, ${FirebaseAuth.getInstance().currentUser!!.displayName}",
                    style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Track your expenses, start your day right",
                    style = MaterialTheme.typography.titleSmall, color = Color.Gray
                )
            }
        })
    }) {
        //for today
        val todayStart = LocalDate.now().atStartOfDay().toString() // Midnight of today
        val todayEnd = LocalDate.now().plusDays(1).atStartOfDay().toString() // Midnight of tomorrow

        //for last 7 days
        val lastWeekStart = LocalDate.now().minusWeeks(1).atStartOfDay().toString()

        //for this month
        val lastMonthStart =
            LocalDate.now().minusMonths(1).atStartOfDay().toString()



        if (selectedTime == "Today") {
            FirebaseFirestore.getInstance().collection("Expense")
                .whereGreaterThanOrEqualTo("date", todayStart)
                .whereLessThan("date", todayEnd)
                .addSnapshotListener { value, e ->
                    expenses.clear()
                    totalAmount = 0
                    if (e != null) {

                        return@addSnapshotListener
                    }


                    for (doc in value!!) {
                        expenses.add(
                            Expense(
                                title = doc.getString("title") ?: "",
                                category = doc.getString("category") ?: "",
                                id = doc.getString("id") ?: "",
                                date = doc.getString("date") ?: "",
                                userId = doc.getString("userId") ?: "",
                                expense = doc.getLong("expense") ?: 0,


                                )
                        )
                        totalAmount += doc.getLong("expense") ?: 0
                    }
                }
        }

        if (selectedTime == "Last 7 Days") {
            FirebaseFirestore.getInstance().collection("Expense")
                .whereGreaterThanOrEqualTo("date", lastWeekStart)
                .whereLessThan("date", todayEnd)
                .addSnapshotListener { value, e ->
                    expenses.clear()
                    totalAmount = 0
                    if (e != null) {

                        return@addSnapshotListener
                    }


                    for (doc in value!!) {
                        expenses.add(
                            Expense(
                                title = doc.getString("title") ?: "",
                                category = doc.getString("category") ?: "",
                                id = doc.getString("id") ?: "",
                                date = doc.getString("date") ?: "",
                                userId = doc.getString("userId") ?: "",
                                expense = doc.getLong("expense") ?: 0,


                                )
                        )
                        totalAmount += doc.getLong("expense") ?: 0
                    }
                }
        }

        if (selectedTime == "This Month") {
            FirebaseFirestore.getInstance().collection("Expense")
                .whereGreaterThanOrEqualTo("date", lastMonthStart)
                .whereLessThan("date", todayEnd)
                .addSnapshotListener { value, e ->
                    expenses.clear()
                    totalAmount = 0
                    if (e != null) {

                        return@addSnapshotListener
                    }


                    for (doc in value!!) {
                        expenses.add(
                            Expense(
                                title = doc.getString("title") ?: "",
                                category = doc.getString("category") ?: "",
                                id = doc.getString("id") ?: "",
                                date = doc.getString("date") ?: "",
                                userId = doc.getString("userId") ?: "",
                                expense = doc.getLong("expense") ?: 0,


                                )

                        )
                        totalAmount += doc.getLong("expense") ?: 0
                    }
                }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {


            LazyRow()
            {
                itemsIndexed(selectedTimeList)
                { index, time ->
                    ElevatedButton(
                        modifier = Modifier
                            .height(38.dp)
                            .padding(horizontal = 5.dp),
                        onClick = { selectedTime = selectedTimeList[index] },
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selectedTime == selectedTimeList[index]) Color(
                                0xff24282D
                            ) else (Color(0xffE1E7EF))
                        )
                    ) {


                        Text(
                            text = time, style = MaterialTheme.typography.bodyLarge,
                            color = if (selectedTime == selectedTimeList[index]) Color.White else (Color.Black)
                        )


                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            Card(
                modifier = Modifier
                    .padding(10.dp)
                    .height(100.dp)
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xff1C1C25))
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {


                    Text(
                        modifier = Modifier.padding(0.dp),
                        text = "Amount Spent",
                        color = Color(0xff878D98),
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Text(
                        totalAmount.toString(),
                        color = Color.White,
                        style = MaterialTheme.typography.displayMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            if (selectedTime == "Today") {
                Text(
                    modifier = Modifier.padding(start = 3.dp),
                    text = "Today, $today",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )

            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)

            )
            {
                itemsIndexed(expenses) { index, expense ->
                    Column(modifier = Modifier.padding(bottom = 15.dp)) {

                        //for converting iso string to time
                        val formatter = DateTimeFormatter.ISO_DATE_TIME
                        val dateTime = LocalDateTime.parse(expense.date, formatter)

                        val timeFormatter = DateTimeFormatter.ofPattern("hh:mm a")
                        val formattedTime = dateTime.format(timeFormatter)

                        //for converting iso string to date
                        val formattedDateTime =
                            LocalDateTime.parse(expense.date, DateTimeFormatter.ISO_DATE_TIME)
                        var date = formattedDateTime.toLocalDate()

                        val formattedDate = formatDateWithSuffix(date)

                        var isNewDate = date != dailyDate
                        if (isNewDate) {
                            dailyDate = date
                        }
                        if (selectedTime != "Today") {
                            if (isNewDate) {
                                Text(
                                    modifier = Modifier
                                        .align(Alignment.CenterHorizontally)
                                        .padding(bottom = 3.dp),
                                    text = formattedDate,
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column() {


                                Text(
                                    text = expense.title,
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = formattedTime,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                            Text(
                                text = "$ ${expense.expense}",
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }

    }
}

fun formatDateWithSuffix(date: LocalDate): String {
    val dayOfMonth = date.dayOfMonth
    val suffix = getOrdinalSuffix(dayOfMonth)
    val month = date.month.toString().toLowerCase().capitalize()
    val year = date.year

    return "$dayOfMonth$suffix $month $year"
}

fun getOrdinalSuffix(n: Int): String {
    return when {
        n in 11..13 -> "th"
        n % 10 == 1 -> "st"
        n % 10 == 2 -> "nd"
        n % 10 == 3 -> "rd"
        else -> "th"
    }
}