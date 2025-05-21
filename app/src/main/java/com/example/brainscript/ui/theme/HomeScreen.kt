package com.example.brainscript.ui.theme

import android.content.Intent
import android.widget.Toast
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

@Composable
fun HomeScreen(loggedUser: User, viewModel: HomeVModel) {
    val context = LocalContext.current
    val menuItems = viewModel.items.collectAsState(initial = emptyList())
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF0D1B2A), Color(0xFF1B263B))
                )
            ),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
                .padding(bottom = 1.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome <Name>",
                color = Color.Yellow,
                fontSize = 30.sp,
                modifier = Modifier.padding(bottom = 20.dp)
            )
            menuItems.value.forEach{ item ->
                Button(
                    onClick = {
                        if (item=="Contact Us"){
                            val intent = Intent(Intent.ACTION_SEND).apply{
                                type = "message/rfc882"
                                putExtra(Intent.EXTRA_EMAIL, arrayOf("haris.imamovic@stu.ibu.edu.ba"))
                                putExtra(Intent.EXTRA_SUBJECT, "Contacting BrainScript Team")
                                setPackage("com.google.android.gm")
                            }
                            try{
                                context.startActivity(intent)
                            }catch(e: Exception){
                                Toast.makeText(context, "Cannot find the Gmail app", Toast.LENGTH_LONG).show()
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                        .height(50.dp)
                        .padding(bottom = 10.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow)
                ) {
                    Text(text = item, color = Color(0xFF1B263B), fontSize = 20.sp)
                }
            }
            Spacer(modifier=Modifier.height(16.dp))
            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 2.dp,
                        color = Color.Yellow,
                        shape = RoundedCornerShape(10.dp)
                    ),
                shape= RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
            ) {
                Text(text = "Logout", color = Color.Yellow, fontSize = 20.sp)
            }
        }
    }
}