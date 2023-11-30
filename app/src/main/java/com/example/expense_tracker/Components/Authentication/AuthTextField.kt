package com.example.expense_tracker.Components.Authentication

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun AuthTextField(
    value: String,
    valueChange: (String) -> Unit,
    hint: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
    ),
    icon: ImageVector
) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp),
        value = value,
        onValueChange = valueChange,
        placeholder = { Text(text = hint, color = Color.Gray) },
        keyboardOptions = keyboardOptions,
        leadingIcon = { Icon(icon, contentDescription = "icon", Modifier.size(25.dp)) },
        colors = TextFieldDefaults.colors(

            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedIndicatorColor = Color.DarkGray,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
    )
}