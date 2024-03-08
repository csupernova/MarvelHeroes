package com.example.marvelheroes

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.marvelheroes.data.DataSource
import com.example.marvelheroes.ui.MainScreen
import com.example.marvelheroes.ui.SecondScreen
import com.example.marvelheroes.ui.SelectViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

enum class AppRoutes {
    Start,
    Detailed
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MarvelHeroesApp(
    viewModel: SelectViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val uiState by viewModel.uiState.collectAsState()

    NavHost(
        navController = navController,
        startDestination = AppRoutes.Start.name,
        ) {
        composable(route=AppRoutes.Start.name) {
            MainScreen(
                onCardClick = {
                    viewModel.setCard(it)
                    navController.navigate(AppRoutes.Detailed.name) },
                modifier = Modifier.fillMaxSize(),
                heroes = DataSource.heroes
            )
        }
        composable(route=AppRoutes.Detailed.name) {
            SecondScreen(
                hero = DataSource.heroes[uiState.indexSelected],
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() })
        }
    }
}