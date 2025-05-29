package com.example.brainscript.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.brainscript.vmodels.UserViewModel

@Composable
fun ResultsScreen(score: Int, totalQuestions: Int, userName: String, userId: Int, onTryAgain: () -> Unit, onBackToCategories: () -> Unit, userViewModel: UserViewModel,) {

    val darkBlue = Color(0xFF0D1B2A)
    val lightBlue = Color(0xFF778DA9)
    val mediumBlue = Color(0xFF1B263B)

    val message = when {
        score == totalQuestions -> "Perfect!"
        score >= totalQuestions * 0.7 -> "Well done!"
        score >= totalQuestions * 0.5 -> "Not bad, keep practicing!"
        else -> "Don't give up! Try again!"
    }

    LaunchedEffect(Unit) {
        userViewModel.updateScore(userId, score)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(darkBlue)
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Quiz completed, $userName!",
                color = lightBlue,
                fontSize = 30.sp,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = message,
                color = lightBlue,
                fontSize = 21.sp,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(18.dp))
            Text(
                text = "You scored $score out of $totalQuestions",
                color = lightBlue,
                fontSize = 21.sp,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(35.dp))
            Button(
                onClick = onTryAgain,
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(55.dp),
                colors = ButtonDefaults.buttonColors(containerColor = lightBlue)
            ) {
                Text("Try Again", fontSize = 17.sp, color = darkBlue, style = MaterialTheme.typography.titleLarge)
            }
            Spacer(modifier = Modifier.height(15.dp))
            Button(
                onClick = onBackToCategories,
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(54.dp),
                colors = ButtonDefaults.buttonColors(containerColor = lightBlue)
            ) {
                Text("Back to Categories", fontSize = 17.sp, color = darkBlue, style = MaterialTheme.typography.titleLarge)
            }
        }
    }
}
