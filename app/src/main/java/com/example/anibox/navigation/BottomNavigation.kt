package com.example.anibox.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.anibox.R
import com.example.anibox.presenter.home.HomeScreen
import com.example.anibox.presenter.myList.MyList
import com.example.anibox.presenter.notifications.Notifications
import com.example.anibox.presenter.profile.Profile
import com.example.anibox.presenter.search.Search
import com.example.anibox.ui.theme.blacker
import com.example.anibox.ui.theme.smokyWhite

enum class BottomNavTabs(val label: String,val icon: Int){
    Home("Home", R.drawable.home,),
    Search("Search", R.drawable.loupe,),
    MyList("Bookmark ", R.drawable.heart,),
    Notifications("Notifications", R.drawable.bell,),
    Profile("Profile", R.drawable.account)
}

@Composable
fun NavScreen(
    selectedTab: MutableState<BottomNavTabs>,
    navController: NavHostController
) {
    Scaffold(
        bottomBar = {
            if (!isSystemInDarkTheme()) {
                BottomNavigation(
                    modifier = Modifier.height(48.dp),
                    backgroundColor = smokyWhite,
                ) {
                    for (tab in BottomNavTabs.values()) {
                        BottomNavigationItem(
                            modifier = Modifier.fillMaxSize(),
                            selected = selectedTab.value == tab,
                            onClick = {
                                if (selectedTab.value == tab) return@BottomNavigationItem
                                selectedTab.value = tab
                            },
                            label = {
                                Text(
                                    text = tab.label,
                                    fontSize = 10.sp,
                                    color = Color.Black,
                                )
                            },
                            alwaysShowLabel = false,
                            icon = {
                                Image(
                                    painterResource(id = tab.icon),
                                    colorFilter = ColorFilter.tint(Color.Black),
                                    contentDescription = tab.label,
                                    modifier = Modifier.size(20.dp),
                                    contentScale = ContentScale.Crop
                                )

                            },
                        )
                    }
                }
            } else {
                BottomNavigation(
                    modifier = Modifier.height(48.dp),
                    backgroundColor = blacker,
                    elevation = 16.dp
                ) {
                    for (tab in BottomNavTabs.values()) {
                        BottomNavigationItem(
                            modifier = Modifier.fillMaxSize(),
                            selected = selectedTab.value == tab,
                            onClick = {
                                if (selectedTab.value == tab) return@BottomNavigationItem
                                selectedTab.value = tab
                            },
                            label = {
                                Text(
                                    text = tab.label,
                                    fontSize = 10.sp,
                                    color = Color.White,
                                )
                            },
                            alwaysShowLabel = false,
                            icon = {
                                Image(
                                    painterResource(id = tab.icon),
                                    colorFilter = ColorFilter.tint(Color.White),
                                    contentDescription = tab.label,
                                    modifier = Modifier.size(20.dp),
                                    contentScale = ContentScale.Crop
                                )

                            },
                        )
                    }
                }
            }
        }
    ) {
        val modifier = Modifier.padding(it)
        when (selectedTab.value) {
            BottomNavTabs.Home -> HomeScreen(
                navController,
                modifier = modifier
            )
            BottomNavTabs.Search -> Search(
                modifier,
                navController
            )
            BottomNavTabs.MyList -> MyList(
                modifier,
                navController
            )
            BottomNavTabs.Notifications -> Notifications(
                modifier,
                navController
            )
            BottomNavTabs.Profile -> Profile(
                navController = navController,
                modifier
            )
        }
    }

}














