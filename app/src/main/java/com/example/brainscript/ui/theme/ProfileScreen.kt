package com.example.brainscript.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.brainscript.model.User
import com.example.brainscript.vmodels.UserViewModel

@Composable
fun ProfileScreen(loggedUser: User?, userViewModel: UserViewModel) {
    val userState by userViewModel.loggedUser.collectAsState()

    LaunchedEffect(loggedUser?.id) {
        loggedUser?.id?.let { userViewModel.loadUserById(it) }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF0D1B2A), Color(0xFF1B263B))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        when {
            loggedUser == null -> {
                Text(
                    text = "No user logged in",
                    color = Color.Red,
                    fontSize = 18.sp
                )
            }
            userState == null -> {
                CircularProgressIndicator(color = Color.Yellow)
            }
            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Profile",
                        color = Color.Yellow,
                        fontSize = 28.sp,
                        modifier = Modifier.padding(bottom = 24.dp)
                    )

                    Text(text = "First Name: ${userState!!.firstName}", color = Color.White, fontSize = 20.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Last Name: ${userState!!.lastName}", color = Color.White, fontSize = 20.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Email: ${userState!!.email}", color = Color.White, fontSize = 20.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Phone: ${userState!!.phoneNumber}", color = Color.White, fontSize = 20.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Total Score: ${userState!!.totalScore}", color = Color.White, fontSize = 20.sp)
                }
            }
        }
    }
}
