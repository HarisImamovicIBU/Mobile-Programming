package com.example.brainscript.ui.theme
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.brainscript.R

@Composable
fun RegistrationScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    Box(
        modifier = Modifier.fillMaxSize().background(
            brush = Brush.verticalGradient(
                colors = listOf(Color(0xFF0D1B2A), Color(0xFF1B263B))
            )
        ),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 30.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){ Text(text = "Create Your Account", color = Color.Yellow, fontSize = 24.sp)
            Spacer(modifier = Modifier.height(16.dp))
            InputField(value = email, onValueChange = {
                email = it
                emailError = if (isValidEmail(it)) "" else "Please enter a valid email!"
            }, label = "Email", icon = Icons.Default.Email, errorMessage = emailError)
            InputField(value = firstName, onValueChange = { firstName = it }, label = "First Name", icon = Icons.Default.AccountCircle)
            InputField(value = lastName, onValueChange = { lastName = it }, label = "Last Name", icon = Icons.Default.AccountCircle)
            InputField(value = phoneNumber, onValueChange = { phoneNumber = it }, label = "Phone Number", icon = Icons.Default.Call)
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password", color = Color.Yellow) },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = Color.Yellow) },
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = if (passwordVisible) Icons.Filled.Lock else Icons.Filled.Lock,
                            contentDescription = "Toggle password visibility",
                            tint = Color.Yellow
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    focusedTextColor = Color.Yellow,
                    unfocusedTextColor = Color.Yellow,
                    focusedIndicatorColor = Color.Yellow
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    Toast.makeText(context, "Registration Successful!", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow)
            ) {Text(text = "Register", color = Color(0xFF1B263B), fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Already have an account? Log in",
                color = Color.Yellow,
                modifier = Modifier.clickable { /* Navigate to login */ }
            )
        }
    }
}

@Composable
fun InputField(value: String, onValueChange: (String) -> Unit, label: String, icon: androidx.compose.ui.graphics.vector.ImageVector, errorMessage: String = "") {
    Column {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label, color = Color.Yellow) },
            leadingIcon = { Icon(icon, contentDescription = null, tint = Color.Yellow) },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                focusedTextColor = Color.Yellow,
                focusedIndicatorColor = Color.Yellow,
                unfocusedTextColor = Color.Yellow
            )
        )
        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = Color.Red, modifier = Modifier.padding(start = 10.dp, top = 5.dp))
        }
    }
}
fun isValidEmail(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

