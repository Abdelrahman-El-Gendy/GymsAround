package com.example.gymsaround.Gyms.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.gymsaround.Gyms.presentation.details.GymDetailsScreen
import com.example.gymsaround.Gyms.presentation.gymslist.GymsScreen
import com.example.gymsaround.Gyms.presentation.gymslist.GymsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GymsAroundApp()
        }
    }
}

@Composable
fun GymsAroundApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "gyms"
    ) {
        composable(route = "gyms") {
            val vm: GymsViewModel = hiltViewModel()
            GymsScreen(
                state = vm.state.value,
                onItemClick = { id ->
                    navController.navigate("gyms/$id")
                }, onFavouriteIconClick = { id, oldState ->
                    vm.toggleFavouriteState(id, oldState)
                }
            )
        }
        composable(
            route = "gyms/{gym_id}",
            arguments = listOf(
                navArgument("gym_id") {
                    type = NavType.IntType
                }
            ),
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "https://www.gymsaround.com/details/{gym_id}"
                }
            )
        ) {
            GymDetailsScreen()
        }
    }
}


