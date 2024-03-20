package com.example.marvelheroes

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.marvelheroes.data.DataSource
import com.example.marvelheroes.ui.mainscreen.MainScreen
import com.example.marvelheroes.ui.secondscreen.SecondScreen
import com.example.marvelheroes.ui.SelectViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

enum class AppRoutes {
    Start,
    Detailed
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController()
) {
    val selectViewModel: SelectViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = AppRoutes.Start.name
    ) {
        composable(route=AppRoutes.Start.name) {
            MainScreen(
                onCardClick = { navController.navigate(AppRoutes.Detailed.name) },
                modifier = Modifier.fillMaxSize(),
                heroes = DataSource.heroes,
                selectViewModel = selectViewModel
            )
        }
        composable(route=AppRoutes.Detailed.name) {
            SecondScreen(
                hero = DataSource.heroes[selectViewModel.uiState.value.indexSelected],
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() },
            )
        }
    }
}