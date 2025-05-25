package com.example.brainscript.ui.theme

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.brainscript.R
import com.example.brainscript.vmodels.UserViewModel

@Composable
fun RegistrationScreen(userViewModel: UserViewModel, onLoginNav: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    val registrationSuccess by userViewModel.registrationSuccess.collectAsState()

    val darkBlue = Color(0xFF0D1B2A)
    val mediumBlue = Color(0xFF1B263B)
    val lightBlue = Color(0xFFB3CDE0)

    LaunchedEffect(registrationSuccess) {
        if (registrationSuccess == true) {
            Toast.makeText(context, "Registration Successful!", Toast.LENGTH_SHORT).show()
            onLoginNav()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF1B263B), Color(0xFF0D1B2A)),
                    startY = 200f,
                    endY = 1700f
                )
            ),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 30.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(190.dp)
                    .padding(bottom = 12.dp),
                contentScale = ContentScale.Fit
            )

            Text(
                text = "Your knowledge journey starts here!",
                color = lightBlue,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 21.dp),
                fontWeight = FontWeight.Medium,
                style = MaterialTheme.typography.bodyLarge,
                fontStyle = FontStyle.Italic
            )

            Text(text = "Create Your Account", color = lightBlue, fontSize = 27.sp,  style = MaterialTheme.typography.titleLarge,)

            Spacer(modifier = Modifier.height(25.dp))

            OutlinedTextField(
                value = firstName,
                onValueChange = { firstName = it },
                label = { Text("First Name", color = lightBlue, style = MaterialTheme.typography.bodyLarge) },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = lightBlue) },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    focusedTextColor = lightBlue,
                    focusedIndicatorColor = lightBlue
                )
            )

            OutlinedTextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text("Last Name", color = lightBlue, style = MaterialTheme.typography.bodyLarge) },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = lightBlue) },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    focusedTextColor = lightBlue,
                    focusedIndicatorColor = lightBlue
                )
            )

            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = { Text("Phone Number", color = lightBlue, style = MaterialTheme.typography.bodyLarge) },
                leadingIcon = { Icon(Icons.Default.Call, contentDescription = null, tint = lightBlue) },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    focusedTextColor = lightBlue,
                    focusedIndicatorColor = lightBlue
                )
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email", color = lightBlue, style = MaterialTheme.typography.bodyLarge) },
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = null, tint = lightBlue) } ,
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    focusedTextColor = lightBlue,
                    focusedIndicatorColor = lightBlue
                )
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password", color = lightBlue, style = MaterialTheme.typography.bodyLarge) },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = lightBlue) },
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = if (passwordVisible) Icons.Filled.Lock else Icons.Filled.Lock,
                            contentDescription = "Toggle password visibility",
                            tint = lightBlue
                        )
                    }
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    focusedTextColor = lightBlue,
                    unfocusedTextColor = lightBlue,
                    focusedIndicatorColor = lightBlue
                )
            )


            Spacer(modifier = Modifier.height(18.dp))

            Button(
                onClick = {
                    if (email.isNotBlank() && password.isNotBlank() && firstName.isNotBlank() && lastName.isNotBlank() && phoneNumber.isNotBlank() && emailError.isEmpty()) {
                        userViewModel.register(
                            email = email,
                            password = password,
                            firstName = firstName,
                            lastName = lastName,
                            phoneNumber = phoneNumber,
                            totalScore = 0
                        )
                    } else {
                        Toast.makeText(context, "Please fill all fields correctly!", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = lightBlue)
            ) {
                Text(text = "Register", color = darkBlue, fontSize = 20.sp, style = MaterialTheme.typography.bodyLarge)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Already have an account? Log in",
                color = lightBlue,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.clickable { onLoginNav() }
            )
        }
    }
}

fun isValidEmail(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}
