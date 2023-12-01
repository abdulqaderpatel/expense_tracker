package com.example.expense_tracker.Main

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.expense_tracker.Navigation.BottomBarScreen
import com.example.expense_tracker.Navigation.MainNavGraph
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import com.vanpra.composematerialdialogs.title
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UserNavigation(navController: NavHostController = rememberNavController()) {
    var isBottomSheetOpen by remember {
        mutableStateOf(false)
    }
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }, floatingActionButton = {
            FloatingActionButton(onClick = { isBottomSheetOpen = true }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
            if (isBottomSheetOpen) {
                ModalBottomSheet(onDismissRequest = { isBottomSheetOpen = false }) {
                    var amount by remember {
                        mutableStateOf("")
                    }


                    var pickedDate by remember {
                        mutableStateOf(LocalDate.now())
                    }

                    var pickedTime by remember {
                        mutableStateOf(LocalTime.now())
                    }
                    val dateTime: LocalDateTime = pickedDate.atTime(pickedTime)

                    val dateDialogState = rememberMaterialDialogState()
                    val timeDialogState = rememberMaterialDialogState()

                    var selectedCategory by remember {
                        mutableStateOf("Grocery")
                    }




                    Column(modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)) {
                        Text(
                            modifier = Modifier.padding(start = 12.dp),
                            text = "Cab Charge",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            modifier = Modifier.padding(start = 12.dp),
                            text = "Amount",
                            color = Color.DarkGray
                        )
                        TextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(55.dp),
                            value = amount,
                            onValueChange = { amount = it },
                            placeholder = { Text(text = "Enter amount", color = Color.Gray) },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Next
                            ),
                            colors = TextFieldDefaults.colors(

                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White,
                                focusedIndicatorColor = Color.DarkGray,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent
                            ),
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            modifier = Modifier.padding(start = 12.dp),
                            text = "Date",
                            color = Color.DarkGray
                        )
                        ElevatedButton(onClick = { dateDialogState.show() }) {
                            Text(text = pickedDate.toString())
                        }
                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            modifier = Modifier.padding(start = 12.dp),
                            text = "Time",
                            color = Color.DarkGray
                        )
                        ElevatedButton(onClick = { timeDialogState.show() }) {
                            Text(pickedTime.toString())
                        }
                        MaterialDialog(dialogState = dateDialogState, buttons = {
                            positiveButton(text = "Ok")
                            {

                            }
                            negativeButton(text = "Cancel")
                        }) {
                            datepicker(initialDate = LocalDate.now(), title = "Pick a date")
                            {
                                pickedDate = it
                                Log.d("TAGGGG", dateTime.toString())
                            }
                        }

                        MaterialDialog(dialogState = timeDialogState, buttons = {
                            positiveButton(text = "Ok")
                            {

                            }
                            negativeButton(text = "Cancel")
                        }) {
                            timepicker(initialTime = LocalTime.now(), title = "Pick a Time")
                            {
                                pickedTime = it
                                pickedTime = pickedTime.truncatedTo(ChronoUnit.MINUTES)
                                Log.d("TAGGGG", dateTime.toString())
                            }
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            modifier = Modifier.padding(start = 12.dp),
                            text = "Select Category",
                            color = Color.DarkGray
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            ElevatedButton(
                                modifier = Modifier.height(38.dp),
                                onClick = { selectedCategory = "Grocery" },
                                shape = RectangleShape,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (selectedCategory == "Grocery") Color(
                                        0xff24282D
                                    ) else (Color(0xffE1E7EF))
                                )
                            ) {
                                Text(
                                    text = "Grocery", style = MaterialTheme.typography.bodyLarge,
                                    color = if (selectedCategory == "Grocery") Color.White else (Color.Black)
                                )
                            }
                            ElevatedButton(
                                modifier = Modifier.height(38.dp),
                                onClick = { selectedCategory = "Shopping" },
                                shape = RectangleShape,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (selectedCategory == "Shopping") Color(
                                        0xff24282D
                                    ) else (Color(0xffE1E7EF))
                                )
                            ) {
                                Text(
                                    text = "Shopping", style = MaterialTheme.typography.bodyLarge,
                                    color = if (selectedCategory == "Shopping") Color.White else (Color.Black)
                                )
                            }

                            ElevatedButton(
                                modifier = Modifier.height(38.dp),
                                onClick = { selectedCategory = "Recharge" },
                                shape = RectangleShape,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (selectedCategory == "Recharge") Color(
                                        0xff24282D
                                    ) else (Color(0xffE1E7EF))
                                )
                            ) {
                                Text(
                                    text = "Recharge", style = MaterialTheme.typography.bodyLarge,
                                    color = if (selectedCategory == "Recharge") Color.White else (Color.Black)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            ElevatedButton(
                                modifier = Modifier.height(38.dp),
                                onClick = { selectedCategory = "Food" },
                                shape = RectangleShape,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (selectedCategory == "Food") Color(
                                        0xff24282D
                                    ) else (Color(0xffE1E7EF))
                                )
                            ) {
                                Text(
                                    text = "Food", style = MaterialTheme.typography.bodyLarge,
                                    color = if (selectedCategory == "Food") Color.White else (Color.Black)
                                )
                            }
                            ElevatedButton(
                                modifier = Modifier.height(38.dp),
                                onClick = { selectedCategory = "Entertainment" },
                                shape = RectangleShape,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (selectedCategory == "Entertainment") Color(
                                        0xff24282D
                                    ) else (Color(0xffE1E7EF))
                                )
                            ) {
                                Text(
                                    text = "Entertainment",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = if (selectedCategory == "Entertainment") Color.White else (Color.Black),

                                )
                            }

                            ElevatedButton(
                                modifier = Modifier.height(38.dp),
                                onClick = { selectedCategory = "Travelling" },
                                shape = RectangleShape,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (selectedCategory == "Travelling") Color(
                                        0xff24282D
                                    ) else (Color(0xffE1E7EF))
                                )
                            ) {
                                Text(
                                    text = "Travelling", style = MaterialTheme.typography.bodyLarge,
                                    color = if (selectedCategory == "Travelling") Color.White else (Color.Black)
                                )
                            }
                        }
                    }

                }
            }
        }
    ) {
        MainNavGraph(navController = navController)
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Insight,

        )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
    if (bottomBarDestination) {
        NavigationBar {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    NavigationBarItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,

        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}

