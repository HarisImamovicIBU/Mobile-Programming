package com.example.brainscript.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.brainscript.R
import com.example.brainscript.model.Answer
import com.example.brainscript.model.User
import com.example.brainscript.ui.theme.navigation.ResultRoute
import com.example.brainscript.vmodels.AnswerViewModel
import com.example.brainscript.vmodels.CategoryViewModel
import com.example.brainscript.vmodels.QuestionViewModel

@Composable
fun QuizScreen(
    categoryId: Int,
    user: User,
    navController: NavController,
    viewModel: QuestionViewModel = hiltViewModel()
) {
    val questions by viewModel.questions.observeAsState(emptyList())
    val categoryViewModel: CategoryViewModel = hiltViewModel()
    val answerViewModel: AnswerViewModel = hiltViewModel()

    var currentIndex by remember { mutableStateOf(0) }
    var selectedAnswer by remember { mutableStateOf<String?>(null) }
    var score by remember { mutableStateOf(0) }

    LaunchedEffect(categoryId) {
        viewModel.loadQuestionsByCategory(categoryId)
        categoryViewModel.loadCategoryNameById(categoryId)
    }

    val categoryName by categoryViewModel.categoryName.observeAsState("Loading...")

    val darkBlue = Color(0xFF0D1B2A)
    val mediumBlue = Color(0xFF1B263B)
    val lightBlue = Color(0xFF778DA9)
    val backgroundBrush = Brush.verticalGradient(listOf(darkBlue, mediumBlue, lightBlue))

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = backgroundBrush)
            .padding(16.dp)
    ) {
        if (questions.isNotEmpty() && currentIndex < questions.size) {
            val question = questions[currentIndex]
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(12.dp))

                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier.size(185.dp)
                )

                Spacer(modifier = Modifier.height(22.dp))
                Text(
                    text = "BrainScript",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontSize = 33.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(13.dp))
                Text(
                    text = "Question ${currentIndex + 1} of ${questions.size}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = lightBlue,
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.height(13.dp))
                Text(
                    text = "Category: $categoryName",
                    color = Color.White,
                    fontSize = 16.sp
                )

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = question.text,
                            fontSize = 18.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(bottom = 13.dp)
                        )

                        listOf(
                            question.optionA,
                            question.optionB,
                            question.optionC,
                            question.optionD
                        ).forEach { option ->
                            OutlinedButton(
                                onClick = { selectedAnswer = option },
                                colors = ButtonDefaults.outlinedButtonColors(
                                    containerColor = if (selectedAnswer == option) darkBlue else Color.Transparent,
                                    contentColor = Color.White
                                ),
                                shape = RoundedCornerShape(8.dp),
                                border = ButtonDefaults.outlinedButtonBorder,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 5.dp)
                            ) {
                                Text(option ?: "", fontSize = 15.sp)
                            }
                        }
                    }
                }

                Button(
                    onClick = {
                        answerViewModel.insertAnswer(
                            Answer(
                                userId = user.id,
                                questionId = question.id,
                                givenAnswer = selectedAnswer ?: "",
                                isCorrect = selectedAnswer == question.correctAnswer
                            )
                        )

                        if (selectedAnswer == question.correctAnswer) {
                            score++
                        }

                        if (currentIndex < questions.lastIndex) {
                            currentIndex++
                            selectedAnswer = null
                        } else {
                            navController.navigate(
                                ResultRoute(
                                    score = score,
                                    total = questions.size,
                                    user = user
                                )
                            )
                        }
                    },
                    enabled = selectedAnswer != null,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = darkBlue,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp, vertical = 10.dp)
                        .height(45.dp)
                ) {
                    Text(
                        text = if (currentIndex == questions.lastIndex) "Finish" else "Next",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        } else {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = lightBlue)
            }
        }
    }
}
