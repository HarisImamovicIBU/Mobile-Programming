package com.example.brainscript.ui.theme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.brainscript.ui.theme.BrainScriptTheme
import com.example.brainscript.vmodels.HomeVModel

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<HomeVModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BrainScriptTheme {
                //RegistrationScreen()
                //LoginScreen()
                //HomeScreen(viewModel)
            }
        }
    }
}