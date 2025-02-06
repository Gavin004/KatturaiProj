package com.example.katturaiproj

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.katturaiproj.u.SecondScreen
import com.example.katturaiproj.ui.theme.KatturaiProjTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KatturaiProjTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val navController : NavHostController = rememberNavController()
                    NavHost(navController, startDestination = "home"){
                        composable("home"){
                            KatturaiApp(
                                navController = navController
                            )
                        }
                        composable("details/{id}"){ backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
                            if(id!=null){
                                SecondScreen(
                                    id,
                                    navController = navController,
                                    )
                            }
                        }
                    }
//                    KatturaiApp()
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KatturaiProjTheme {
//        Greeting("Android")
    }
}