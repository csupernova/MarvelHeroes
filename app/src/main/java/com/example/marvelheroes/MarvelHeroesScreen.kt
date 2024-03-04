package com.example.marvelheroes

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.marvelheroes.ui.MainScreen

enum class AppRoutes() {
    Start,
    Detailed
}

@Composable
fun MarvelHeroesApp(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = AppRoutes.valueOf(
        backStackEntry?.destination?.route ?: AppRoutes.Start.name
    )

    NavHost(
        navController = navController,
        startDestination = AppRoutes.Start.name,
    ) {
        composable(route=AppRoutes.Start.name) {
            MainScreen(modifier = Modifier.fillMaxSize())
        }
    }

}