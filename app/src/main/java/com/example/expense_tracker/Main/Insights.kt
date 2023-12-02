package com.example.expense_tracker.Main

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.expense_tracker.Models.Expense
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDate


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun Insights() {

    var expenses = mutableStateListOf<Long>(0, 0, 0, 0, 0, 0)
    var floatExpenses = mutableStateListOf<Float>(0f, 0f, 0f, 0f, 0f, 0f)

    var selectedTimeList = listOf<String>("Today", "Last 7 Days", "This Month")
    var selectedTime by remember {
        mutableStateOf("Today")
    }

    //for today
    val todayStart = LocalDate.now().atStartOfDay().toString() // Midnight of today
    val todayEnd = LocalDate.now().plusDays(1).atStartOfDay().toString() // Midnight of tomorrow

    //for last 7 days
    val lastWeekStart = LocalDate.now().minusWeeks(1).atStartOfDay().toString()

    //for this month
    val lastMonthStart = LocalDate.now().minusMonths(1).atStartOfDay().toString()



    if (selectedTime == "Today") {
        FirebaseFirestore.getInstance().collection("Expense")
            .whereEqualTo(
                "userId", FirebaseAuth.getInstance().currentUser!!.uid
            )
            .whereGreaterThanOrEqualTo("date", todayStart).whereLessThan("date", todayEnd)
            .addSnapshotListener { value, e ->


                if (e != null) {

                    return@addSnapshotListener
                }

                for (doc in value!!) {
                    if ((doc.getString("category") ?: "") == "Grocery") {
                        expenses[0] += doc.getLong("expense") ?: 0
                        floatExpenses[0] += expenses[0].toFloat()
                    }
                    if (doc.getString("category") ?: "" == "Shopping") {
                        expenses[1] += doc.getLong("expense") ?: 0
                        floatExpenses[1] += expenses[1].toFloat()
                    }
                    if (doc.getString("category") ?: "" == "Food") {
                        expenses[2] += doc.getLong("expense") ?: 0
                        floatExpenses[2] += expenses[2].toFloat()
                    }
                    if (doc.getString("category") ?: "" == "Recharge") {
                        expenses[3] += doc.getLong("expense") ?: 0
                        floatExpenses[3] += expenses[3].toFloat()
                    }
                    if (doc.getString("category") ?: "" == "Entertainment") {
                        expenses[4] += doc.getLong("expense") ?: 0
                        floatExpenses[4] += expenses[4].toFloat()
                    }
                    if (doc.getString("category") ?: "" == "Travelling") {
                        expenses[5] += doc.getLong("expense") ?: 0
                        floatExpenses[5] += expenses[5].toFloat()
                    }
                }

            }
    }

    if (selectedTime == "Last 7 Days") {
        FirebaseFirestore.getInstance().collection("Expense")
            .whereEqualTo(
                "userId", FirebaseAuth.getInstance().currentUser!!.uid
            )
            .whereGreaterThanOrEqualTo("date", lastWeekStart).whereLessThan("date", todayEnd)
            .addSnapshotListener { value, e ->


                if (e != null) {

                    return@addSnapshotListener
                }

                for (doc in value!!) {
                    if ((doc.getString("category") ?: "") == "Grocery") {
                        expenses[0] += doc.getLong("expense") ?: 0
                        floatExpenses[0] += expenses[0].toFloat()
                    }
                    if (doc.getString("category") ?: "" == "Shopping") {
                        expenses[1] += doc.getLong("expense") ?: 0
                        floatExpenses[1] += expenses[1].toFloat()
                    }
                    if (doc.getString("category") ?: "" == "Food") {
                        expenses[2] += doc.getLong("expense") ?: 0
                        floatExpenses[2] += expenses[2].toFloat()
                    }
                    if (doc.getString("category") ?: "" == "Recharge") {
                        expenses[3] += doc.getLong("expense") ?: 0
                        floatExpenses[3] += expenses[3].toFloat()
                    }
                    if (doc.getString("category") ?: "" == "Entertainment") {
                        expenses[4] += doc.getLong("expense") ?: 0
                        floatExpenses[4] += expenses[4].toFloat()
                    }
                    if (doc.getString("category") ?: "" == "Travelling") {
                        expenses[5] += doc.getLong("expense") ?: 0
                        floatExpenses[5] += expenses[5].toFloat()
                    }
                }

            }
    }

    if (selectedTime == "This Month") {
        FirebaseFirestore.getInstance().collection("Expense")
            .whereEqualTo(
                "userId", FirebaseAuth.getInstance().currentUser!!.uid
            )
            .whereGreaterThanOrEqualTo("date", lastMonthStart).whereLessThan("date", todayEnd)
            .addSnapshotListener { value, e ->


                if (e != null) {

                    return@addSnapshotListener
                }

                for (doc in value!!) {
                    if ((doc.getString("category") ?: "") == "Grocery") {
                        expenses[0] += doc.getLong("expense") ?: 0
                        floatExpenses[0] += expenses[0].toFloat()
                    }
                    if (doc.getString("category") ?: "" == "Shopping") {
                        expenses[1] += doc.getLong("expense") ?: 0
                        floatExpenses[1] += expenses[1].toFloat()
                    }
                    if (doc.getString("category") ?: "" == "Food") {
                        expenses[2] += doc.getLong("expense") ?: 0
                        floatExpenses[2] += expenses[2].toFloat()
                    }
                    if (doc.getString("category") ?: "" == "Recharge") {
                        expenses[3] += doc.getLong("expense") ?: 0
                        floatExpenses[3] += expenses[3].toFloat()
                    }
                    if (doc.getString("category") ?: "" == "Entertainment") {
                        expenses[4] += doc.getLong("expense") ?: 0
                        floatExpenses[4] += expenses[4].toFloat()
                    }
                    if (doc.getString("category") ?: "" == "Travelling") {
                        expenses[5] += doc.getLong("expense") ?: 0
                        floatExpenses[5] += expenses[5].toFloat()
                    }
                }

            }
    }

    Scaffold(topBar = {
        TopAppBar(title = {

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Expense Report",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )


        })
    })
    {
        Column(
            modifier = Modifier.padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            LazyRow() {
                itemsIndexed(selectedTimeList) { index, time ->
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
                            text = time,
                            style = MaterialTheme.typography.bodyLarge,
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
                        expenses.sum().toString(),
                        color = Color.White,
                        style = MaterialTheme.typography.displayMedium,
                        fontWeight = FontWeight.Bold
                    )
                }

            }
            PieChart(
                floatExpenses
            )

        }
    }
}

@Composable
fun PieChart(
    values: List<Float>,
    colors: List<Color> = listOf(
        Color(0xFF58BDFF),
        Color.Green,
        Color.Blue,
        Color.Red,
        Color.DarkGray,
        Color(0xFF092D40)
    ),
    legend: List<String> = listOf(
        "Grocery",
        "Shopping",
        "Recharge",
        "Food",
        "Entertainment",
        "Travelling"
    ),
    size: Dp = 200.dp
) {
    // Sum of all the values
    val sumOfValues = values.sum()

    // Calculate each proportion value
    val proportions = values.map {
        it * 100 / sumOfValues
    }

    // Convert each proportions to angle
    val sweepAngles = proportions.map {
        it * 360 / 100
    }

    Canvas(
        modifier = Modifier.size(size = size)
    ) {
        var startAngle = -90f

        for (i in sweepAngles.indices) {
            drawArc(
                color = colors[i],
                startAngle = startAngle,
                sweepAngle = sweepAngles[i],
                useCenter = true
            )
            startAngle += sweepAngles[i]
        }
    }

    Spacer(modifier = Modifier.height(32.dp))

    Column {
        for (i in values.indices) {
            DisplayLegend(color = colors[i], legend = legend[i])
        }
    }
}

@Composable
fun DisplayLegend(color: Color, legend: String) {
    Row(
        horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(
            modifier = Modifier.width(16.dp), thickness = 4.dp, color = color
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = legend, color = Color.Black
        )
    }
}



