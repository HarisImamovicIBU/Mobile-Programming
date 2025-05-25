package com.example.brainscript.ui.theme

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.brainscript.R
import com.example.brainscript.model.User
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale


@Composable
fun HomeScreen(
    loggedUser: User,
    onStartQuiz: () -> Unit,
    onViewQuestions: () -> Unit,
    onProfile: () -> Unit,
    onLogout: () -> Unit
) {
    val context = LocalContext.current

    val darkBlue = Color(0xFF0D1B2A)
    val mediumBlue = Color(0xFF1B263B)
    val lightBlue = Color(0xFFB3CDE0)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(darkBlue, lightBlue),
                    startY = 675f,
                    endY = 3300f
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp, vertical = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "App Logo",
                modifier = Modifier
                    .height(100.dp)
                    .padding(bottom = 20.dp),
                contentScale = ContentScale.Fit
            )

            Text(
                text = "Welcome ${loggedUser.firstName}",
                color = lightBlue,
                style = MaterialTheme.typography.titleLarge,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 40.dp)
            )

            Button(
                onClick = onStartQuiz,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .height(55.dp),
                colors = ButtonDefaults.buttonColors(containerColor = lightBlue),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("Start Quiz", color = darkBlue, fontSize = 20.sp)
            }

            Button(
                onClick = onViewQuestions,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .height(55.dp),
                colors = ButtonDefaults.buttonColors(containerColor = lightBlue),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("View Questions", color = darkBlue, fontSize = 20.sp)
            }

            Button(
                onClick = onProfile,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .height(55.dp),
                colors = ButtonDefaults.buttonColors(containerColor = lightBlue),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("Profile", color = darkBlue, fontSize = 20.sp)
            }

            Button(
                onClick = {
                    val intent = Intent(Intent.ACTION_SENDTO).apply {
                        data = Uri.parse("mailto:")
                        putExtra(Intent.EXTRA_EMAIL, arrayOf("brainscript.support@gmail.com"))
                        putExtra(Intent.EXTRA_SUBJECT, "Support Request")
                    }
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .height(55.dp),
                colors = ButtonDefaults.buttonColors(containerColor = lightBlue),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("Contact Us", color = darkBlue, fontSize = 20.sp)
            }

            Spacer(modifier = Modifier.height(25.dp))

            Button(
                onClick = onLogout,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .border(2.dp, lightBlue, RoundedCornerShape(10.dp)),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("Logout", color = lightBlue, fontSize = 20.sp)
            }
        }
    }
}
