package com.example.animeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
<<<<<<< HEAD
import com.example.animeapp.navigation.SetupNavGraph
=======
>>>>>>> dcb5e9f101420ddaa793a1d2c26c3a5aae44c30a
import com.example.animeapp.ui.theme.AnimeAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimeAppTheme {
<<<<<<< HEAD
                navController = rememberNavController()
                SetupNavGraph(navController = navController)
=======

                navController = rememberNavController()
>>>>>>> dcb5e9f101420ddaa793a1d2c26c3a5aae44c30a
            }
        }
    }
}