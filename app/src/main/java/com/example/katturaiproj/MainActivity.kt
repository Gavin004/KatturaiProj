package com.example.katturaiproj

import android.os.Bundle
import android.util.Log
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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.katturaiproj.u.AuthorsScreen
import com.example.katturaiproj.u.CategoriesScreen
import com.example.katturaiproj.u.FavoritesScreen
//import com.example.katturaiproj.u.ItemScreen
import com.example.katturaiproj.u.SecondScreen
import com.example.katturaiproj.u.TagsScreen
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
                        composable("details/{id}",){ backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
                            Log.d("HOME","$id")
                            if(id!=null){
                                SecondScreen(id, navController = navController)
                            }
                        }
//                        composable("trending"){
//
//                        }
//                        composable("new"){
//
//                        }
                        composable("favorites/{tabIndex}"){
                            val tabIndex = it.arguments?.getString("tabIndex")?.toIntOrNull()
                            FavoritesScreen(tabIndex as Int,navController = navController)
                        }
                        composable("categories/{tabIndex}"){
                            val tabIndex = it.arguments?.getString("tabIndex")?.toIntOrNull()
                            CategoriesScreen(tabIndex as Int,navController = navController)
                        }
                        composable("authors/{tabIndex}"){
                            val tabIndex = it.arguments?.getString("tabIndex")?.toIntOrNull()
                            AuthorsScreen(tabIndex as Int,navController = navController)
                        }
                        composable("tags/{tabIndex}"){
                            val tabIndex = it.arguments?.getString("tabIndex")?.toIntOrNull()
                            TagsScreen(tabIndex as Int,navController = navController)
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