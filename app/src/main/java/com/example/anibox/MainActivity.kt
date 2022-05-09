package com.example.anibox

import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.anibox.core.DispatchersProvider
import com.example.anibox.navigation.Screen
import com.example.anibox.presenter.account.UserViewModel
import com.example.anibox.presenter.account.login.Login
import com.example.anibox.presenter.account.signUp.SignUp
import com.example.anibox.presenter.home.HomeScreen
import com.example.anibox.presenter.home.HomeViewModel
import com.example.anibox.presenter.splash.SplashScreen
import com.example.anibox.presenter.splash.SplashViewModel
import com.example.anibox.presenter.welcome.WelcomeScreen
import com.example.anibox.presenter.welcome.WelcomeScreenViewModel
import com.example.anibox.ui.theme.AniBoxTheme
import com.example.anibox.ui.theme.blackestBack
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var dispatchers: DispatchersProvider
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AniBoxTheme {
                ProvideWindowInsets {
                    MyApp(
                        window = window,
                        dispatchers = dispatchers
                    )
                }
            }
        }
    }
}


@Composable
fun MyApp(window: Window, dispatchers: DispatchersProvider) {

    val systemUiController = rememberSystemUiController()
    val navController = rememberNavController()
    val homeScrollState = rememberLazyListState()

    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Home.route) {
            OnDestinationChanged(
                systemUiController = systemUiController,
                color = blackestBack,
                drawOverStatusBar = false,
                window = window
            )

            val homeViewModel = hiltViewModel<HomeViewModel>()


            HomeScreen(
                navController = navController,
                viewModel = homeViewModel,
                lazyColumnState = homeScrollState,
                onSearchFieldClick = {}
            )
        }

        composable(Screen.Login.route) {
            OnDestinationChanged(
                systemUiController = systemUiController,
                color = Color.Black,
                drawOverStatusBar = false,
                window = window
            )

            val userViewModel = hiltViewModel<UserViewModel>()


            Login(
                navController = navController,
                userViewModel = userViewModel
            )
        }


        composable(Screen.Welcome.route) {
            OnDestinationChanged(
                systemUiController = systemUiController,
                color = blackestBack,
                drawOverStatusBar = false,
                window = window
            )

            val welcomeScreenViewModel = hiltViewModel<WelcomeScreenViewModel>()

            WelcomeScreen(
                navController = navController,
                welcomeScreenViewModel = welcomeScreenViewModel
            )
        }


        composable(Screen.SignUp.route) {
            OnDestinationChanged(
                systemUiController = systemUiController,
                color = Color.Black,
                drawOverStatusBar = false,
                window = window
            )

            val userViewModel = hiltViewModel<UserViewModel>()

            SignUp(
                navController = navController,
                userViewModel = userViewModel
            )
        }
        
        
        
        composable(Screen.Splash.route){
            OnDestinationChanged(
                systemUiController = systemUiController,
                color = Color.Black,
                drawOverStatusBar = false,
                window = window
            )


            val splashViewModel = hiltViewModel<SplashViewModel>()


            SplashScreen(navController = navController, splashViewModel = splashViewModel)
        }
    }


}
