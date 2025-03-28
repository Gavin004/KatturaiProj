package com.example.katturaiproj

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Composable
fun HandleBackButton(navController: NavController) {
    BackHandler {
        // Always navigate to Home when back is pressed
        navController.navigate("home") {
            popUpTo("home") { inclusive = true }  //
        }
    }
} // used in tagsScreen , favoriteScreen , categoriesScreen , authorScreen ,