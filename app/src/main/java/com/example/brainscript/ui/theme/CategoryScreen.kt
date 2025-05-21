package com.example.brainscript.ui.theme

import android.content.Intent
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.foundation.BorderStroke
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

@Composable
fun CategoryScreen(loggedUser: User, categoryViewModel: CategoryViewModel) {
    val context = LocalContext.current
    val categories by categoryViewModel.categories.collectAsState()
    LaunchedEffect(Unit) {
        categoryViewModel.loadAllCategories()
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF0D1B2A), Color(0xFF1B263B))
                )
            ),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Choose Category",
                color = Color.Yellow,
                fontSize = 28.sp,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            if (categories.isEmpty()) {
                CircularProgressIndicator(color = Color.Yellow)
            } else {
                Column(
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    for (row in categories.chunked(2)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(20.dp)
                        ) {
                            for (category in row) {
                                Card(
                                    modifier = Modifier
                                        .weight(1f)
                                        .aspectRatio(1f),
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color(0xFF415A77)
                                    ),
                                    shape = RoundedCornerShape(16.dp),
                                    onClick = {
                                        Toast
                                            .makeText(context, "Selected: ${category.categoryName}", Toast.LENGTH_SHORT)
                                            .show()
                                    },
                                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                                ) {
                                    Box(
                                        modifier = Modifier.fillMaxSize(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = category.categoryName,
                                            color = Color.White,
                                            fontSize = 16.sp
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