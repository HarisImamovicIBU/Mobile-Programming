package com.example.brainscript.ui.theme

import android.content.Intent
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.brainscript.vmodels.HomeVModel
import androidx.compose.ui.platform.LocalContext
import com.example.brainscript.model.User
import com.example.brainscript.vmodels.CategoryViewModel
import com.google.android.gms.common.logging.Logger
import kotlinx.coroutines.Job
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.brainscript.ui.theme.navigation.Quiz
import com.example.brainscript.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign

@Composable
fun CategoryScreen(
    loggedUser: User,
    categoryViewModel: CategoryViewModel,
    navController: NavController
) {
    val categories by categoryViewModel.categories.collectAsState()

    LaunchedEffect(Unit) {
        categoryViewModel.loadAllCategories()
    }

    val darkBlue = Color(0xFF0D1B2A)
    val mediumBlue = Color(0xFF1B263B)
    val lightBlue = Color(0xFF778DA9)
    val glowBlue = Color(0xFF4FC3F7)
    val backgroundBrush = Brush.verticalGradient(listOf(mediumBlue, lightBlue), startY = 675f, endY = 1700f)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = backgroundBrush)
            .padding(16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(220.dp)
                    .padding(top = 18.dp, bottom = 14.dp)
            )
            Text(
                text = "Choose Category",
                color = Color.White,
                fontSize = 33.sp,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 40.dp)
            )

            Spacer(modifier = Modifier.height(13.dp))

            if (categories.isEmpty()) {
                CircularProgressIndicator(color = Color.White)
            } else {
                Column(verticalArrangement = Arrangement.spacedBy(22.dp)) {
                    for (row in categories.chunked(2)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(18.dp)
                        ) {
                            for (category in row) {
                                Card(
                                    modifier = Modifier
                                        .weight(1f)
                                        .aspectRatio(1.1f),
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color(0xFF0D1B2A)
                                    ),
                                    shape = RoundedCornerShape(20.dp),
                                    elevation = CardDefaults.cardElevation(25.dp),
                                    onClick = {
                                        navController.navigate(Quiz(categoryId = category.id, user = loggedUser))
                                    }
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(12.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = category.categoryName,
                                            color = Color.White,
                                            fontSize = 17.sp,
                                            style = MaterialTheme.typography.titleLarge,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier.fillMaxWidth()
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}