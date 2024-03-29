package com.example.marvelheroes

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.marvelheroes.ui.mainscreen.MainScreen
import com.example.marvelheroes.ui.secondscreen.SecondScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.marvelheroes.ui.mainscreen.MainScreenViewModel

enum class AppRoutes {
    Start,
    Detailed
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController()
) {
    val mainScreenViewModel: MainScreenViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = AppRoutes.Start.name
    ) {
        composable(route=AppRoutes.Start.name) {
            MainScreen(
                onCardClick = { navController.navigate("${AppRoutes.Detailed.name}/$it") },
                modifier = Modifier.fillMaxSize(),
                mainScreenUiState = mainScreenViewModel.mainScreenUiState
            )
        }
        composable(route="${AppRoutes.Detailed.name}/{characterId}") {
            val characterId = it.arguments?.getString("characterId") ?: ""
            SecondScreen(
                characterId = characterId,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() },
            )
        }
    }
}