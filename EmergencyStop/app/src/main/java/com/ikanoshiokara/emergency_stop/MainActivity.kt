package com.ikanoshiokara.emergency_stop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ikanoshiokara.emergency_stop.ui.theme.EmergencyStopTheme
import com.ikanoshiokara.emergency_stop.view.pages.MainPage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EmergencyStopTheme {
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                CompositionLocalProvider(
                    LocalNavController provides navController
                ) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        NavHost(
                            navController = LocalNavController.current,
                            startDestination = NavItem.MainPage.name
                        ) {
                            composable(NavItem.MainPage.name) {
                                MainPage()
                            }
                        }
                    }
                }
            }
        }
    }
}

val LocalNavController = staticCompositionLocalOf<NavHostController> {
    error("No Current NavController")
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    EmergencyStopTheme {
        Greeting("Android")
    }
}