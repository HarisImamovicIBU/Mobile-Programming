package com.example.brainscript.ui.theme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.brainscript.R
import com.example.brainscript.model.User
import com.example.brainscript.vmodels.UserViewModel

@Composable
fun LoginScreen(userViewModel: UserViewModel, onRegisterNav: ()->Unit, onSuccessLogin: (User)->Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val loginStatus by userViewModel.loginStatus.collectAsState()
    val loggedUser by userViewModel.loggedUser.collectAsState()
    var passwordVisible by remember { mutableStateOf(false) }

    val darkBlue = Color(0xFF0D1B2A)
    val mediumBlue = Color(0xFF1B263B)
    val lightBlue = Color(0xFFB3CDE0)

    LaunchedEffect(loginStatus) {
        loginStatus?.let{
                success->
            if(success){
                onSuccessLogin(loggedUser!!)
            }
            else{
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize().background(
            brush = Brush.verticalGradient(
                colors = listOf(Color(0xFF0D1B2A), Color(0xFF1B263B))
            )
        ),
        contentAlignment = Alignment.BottomCenter
    ){

        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.slidza),
            contentDescription = "Login background",
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 33.dp).padding(bottom = 270.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome to BrainScript",
                color = lightBlue,
                fontSize = 38.sp,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 25.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
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
                            contentDescription = if (passwordVisible) "Hide password" else "Show password",
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

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
            ){}

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {userViewModel.login(email, password)},
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(13.dp),
                colors = ButtonDefaults.buttonColors(containerColor = lightBlue
                )
            ) {
                Text(text = "Log In", color = darkBlue, fontSize = 21.sp, style = MaterialTheme.typography.bodyLarge)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalDivider(modifier = Modifier.weight(4f), color = darkBlue)
                Text(text = "or", color = lightBlue, modifier = Modifier.padding(10.dp), style = MaterialTheme.typography.titleLarge, fontSize = 18.sp)
                HorizontalDivider(modifier = Modifier.weight(4f), color = darkBlue)
            }
            Button(
                onClick = {onRegisterNav()},
                modifier = Modifier.fillMaxWidth().
                border(width = 2.dp, color = lightBlue, shape = RoundedCornerShape(13.dp)),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
            ) {
                Text(text = "Register", color = lightBlue, fontSize = 21.sp, style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}
