package com.example.expense_tracker.Main

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home() {

    val expenses = mutableStateListOf<Expense>()

    var selectedTimeList = listOf<String>("Today", "Last 7 Days", "This Month")
    var selectedTime by remember {
        mutableStateOf("Today")
    }







    Scaffold(topBar = {
        TopAppBar(title = {
            Column {
                Text(
                    text = "Good Morning, ${FirebaseAuth.getInstance().currentUser!!.displayName}",
                    style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold
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
            LocalDate.now().minusMonths(1).withDayOfMonth(1).atStartOfDay().toString()
        val thisMonthStart = LocalDate.now().withDayOfMonth(1).atStartOfDay().toString()


        if (selectedTime == "Today") {
            FirebaseFirestore.getInstance().collection("Expense")
                .whereGreaterThanOrEqualTo("date", todayStart)
                .whereLessThan("date", todayEnd)
                .addSnapshotListener { value, e ->
                    expenses.clear()
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
                    }
                }
        }

        if (selectedTime == "Last 7 Days") {
            FirebaseFirestore.getInstance().collection("Expense")
                .whereGreaterThanOrEqualTo("date", lastWeekStart)
                .whereLessThan("date", todayEnd)
                .addSnapshotListener { value, e ->
                    expenses.clear()
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
                    }
                }
        }

        if (selectedTime == "This Month") {
            FirebaseFirestore.getInstance().collection("Expense")
                .whereGreaterThanOrEqualTo("date", lastMonthStart)
                .whereLessThan("date", thisMonthStart)
                .addSnapshotListener { value, e ->
                    expenses.clear()
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

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()

            )
            {
                itemsIndexed(expenses) { index, expense ->
                    Column {


                        val formatter = DateTimeFormatter.ISO_DATE_TIME
                        val dateTime = LocalDateTime.parse(expense.date, formatter)

                        val timeFormatter = DateTimeFormatter.ofPattern("hh:mm a")
                        val formattedTime = dateTime.format(timeFormatter)

                        Text(text = expense.title.toString())
                        Text(text =formattedTime )
                    }
                }
            }
        }

    }
}