package com.example.brainscript.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
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

    // Theme colors
    val darkBlue = Color(0xFF0D1B2A)
    val mediumBlue = Color(0xFF1B263B)
    val lightBlue = Color(0xFFB3CDE0)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(darkBlue, mediumBlue)
                )
            )
            .padding(24.dp)
    ) {
        when {
            loggedUser == null -> {
                Text(
                    text = "No user logged in",
                    color = Color.Red,
                    fontSize = 18.sp,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            userState == null -> {
                CircularProgressIndicator(
                    color = lightBlue,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopCenter),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(40.dp))

                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = "Profile Icon",
                        tint = lightBlue,
                        modifier = Modifier.size(120.dp)
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "${userState!!.firstName} ${userState!!.lastName}",
                        color = lightBlue,
                        fontSize = 24.sp,
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    ProfileInfo(label = "First Name", value = userState!!.firstName, lightBlue)
                    Spacer(modifier = Modifier.height(8.dp))

                    ProfileInfo(label = "Last Name", value = userState!!.lastName, lightBlue)
                    Spacer(modifier = Modifier.height(8.dp))

                    ProfileInfo(label = "Email", value = userState!!.email, lightBlue)
                    Spacer(modifier = Modifier.height(8.dp))

                    ProfileInfo(label = "Phone Number", value = userState!!.phoneNumber, lightBlue)
                    Spacer(modifier = Modifier.height(8.dp))

                    ProfileInfo(label = "Total Score", value = userState!!.totalScore.toString(), lightBlue)
                }
            }
        }
    }
}

@Composable
fun ProfileInfo(label: String, value: String, lightBlue: Color) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color.White.copy(alpha = 0.05f),
                shape = MaterialTheme.shapes.medium
            )
            .padding(14.dp)
    ) {
        Text(
            text = label.uppercase(),
            color = lightBlue.copy(alpha = 0.7f),
            fontSize = 12.sp,
            style = MaterialTheme.typography.labelSmall
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            color = lightBlue,
            fontSize = 18.sp,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
