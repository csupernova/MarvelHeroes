package com.example.marvelheroes.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.example.marvelheroes.presentation.mainscreen.MainScreen
import com.example.marvelheroes.presentation.secondscreen.SecondScreen

enum class AppRoutes {
    Start,
    Detailed
}

const val NAV_URL = "myapp://marvelheroes"

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = AppRoutes.Start.name
    ) {
        composable(route=AppRoutes.Start.name) {
            MainScreen(
                onCardClick = { navController.navigate("${AppRoutes.Detailed.name}/$it") },
                modifier = Modifier.fillMaxSize()
            )
        }
        composable(
            route="${AppRoutes.Detailed.name}/{characterId}",
            deepLinks = listOf(navDeepLink { uriPattern = "$NAV_URL/Detailed/{characterId}" },
                )
        ) {
                SecondScreen(
                    canNavigateBack = navController.previousBackStackEntry != null,
                    navigateUp = { navController.navigateUp() },
                )
        }
    }
}