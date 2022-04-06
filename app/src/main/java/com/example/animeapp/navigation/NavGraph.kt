package com.example.animeapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
<<<<<<< HEAD
import com.example.animeapp.presentation.screens.home.HomeScreen
import com.example.animeapp.presentation.screens.profile.ProfileScreen
=======
>>>>>>> dcb5e9f101420ddaa793a1d2c26c3a5aae44c30a
import com.example.animeapp.presentation.screens.splash.SplashScreen
import com.example.animeapp.presentation.screens.welcome.WelcomeScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
<<<<<<< HEAD

=======
    
>>>>>>> dcb5e9f101420ddaa793a1d2c26c3a5aae44c30a
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }

        composable(route = Screen.Welcome.route) {
            WelcomeScreen(navController = navController)
        }
<<<<<<< HEAD

        composable(route = Screen.Welcome.route) {
            HomeScreen(navController = navController)
        }

        composable(route = Screen.Welcome.route) {
            ProfileScreen(navController = navController)
        }
=======
>>>>>>> dcb5e9f101420ddaa793a1d2c26c3a5aae44c30a
     }
    
}