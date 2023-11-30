package com.example.expense_tracker.Authentication

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.SecurityUpdateGood
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.expense_tracker.Components.Authentication.AuthButton
import com.example.expense_tracker.Components.Authentication.AuthTextField
import com.example.expense_tracker.Navigation.AuthScreen
import com.example.expense_tracker.R

@Composable
fun Signup(navController: NavController = rememberNavController()) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var name by remember {
            mutableStateOf("")
        }

        var email by remember {
            mutableStateOf("")
        }
        var password by remember {
            mutableStateOf("")
        }

        var confirmPassword by remember {
            mutableStateOf("")
        }
        Text(
            text = "Hey There,",
            style = MaterialTheme.typography.titleLarge,

            )

        Text(
            text = "Create an Account",
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold
        )


        Image(
            modifier = Modifier.size(150.dp),
            painter = painterResource(id = R.drawable.appicon),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(20.dp))

        AuthTextField(
            value = name,
            valueChange = { name = it },
            hint = "Enter name",
            icon = Icons.Outlined.Person
        )
        Spacer(modifier = Modifier.height(15.dp))
        AuthTextField(
            value = email,
            valueChange = { email = it },
            hint = "Enter Email",
            icon = Icons.Default.Email
        )
        Spacer(modifier = Modifier.height(15.dp))
        AuthTextField(
            value = password,
            valueChange = { password = it },
            hint = "Enter Password",
            icon = Icons.Default.Password,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        AuthTextField(
            value = confirmPassword,
            valueChange = { confirmPassword = it },
            hint = "Confirm Password",
            icon = Icons.Default.Password,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        AuthButton(modifier = Modifier
            .fillMaxWidth()
            .height(48.dp), onClick = { })
        {
            Text("Register", color = Color.White, fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
        }



        Spacer(modifier = Modifier.height(45.dp))
        Row {
            Text(text = "Already have an account? ")
            Text(
                modifier = Modifier.clickable {
                    navController.popBackStack()
                    navController.navigate(AuthScreen.Login.route)
                },
                text = "Login",
                color = Color(0xff3C93DB)
            )
        }


    }

}


@Preview(showBackground = true)
@Composable
fun signinpreview() {
    Signup()
}
