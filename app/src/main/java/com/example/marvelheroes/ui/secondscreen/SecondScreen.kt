package com.example.marvelheroes.ui.secondscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.marvelheroes.R
import com.example.marvelheroes.network.model.Hero
import com.example.marvelheroes.ui.SecondScreenUiState
import com.example.marvelheroes.ui.SecondScreenViewModel
import com.example.marvelheroes.ui.SelectViewModel
import com.example.marvelheroes.ui.components.AppBar
import com.example.marvelheroes.ui.mainscreen.ErrorScreen
import com.example.marvelheroes.ui.mainscreen.LoadingScreen


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SecondScreen(
    selectViewModel: SelectViewModel,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
) {
    val secondScreenViewModel: SecondScreenViewModel = viewModel()
    secondScreenViewModel.getMarvelInfoHero(selectViewModel.uiState.value.heroId)

    when (val secondScreenUiState = secondScreenViewModel.secondScreenUiState) {
        is SecondScreenUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
        is SecondScreenUiState.Success ->
            ResultScreen(
                hero = secondScreenUiState.hero,
                canNavigateBack = canNavigateBack,
                navigateUp = navigateUp,
            )
        is SecondScreenUiState.Error -> ErrorScreen(modifier = Modifier.fillMaxSize())
    }
}

@Composable
fun ResultScreen(
    hero: Hero,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,

) {
    Box(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = hero.picture,
            error = painterResource(R.drawable.ic_broken_image),
            placeholder = painterResource(R.drawable.loading_img),
            contentDescription = hero.name,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier.fillMaxSize()
        ) {

            Text(
                text = hero.name,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.padding(
                    start = dimensionResource(R.dimen.padding_start_text),
                    bottom = dimensionResource(R.dimen.padding_bot_name)
                ),
            )

            Text(
                text = hero.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.padding(
                    start = dimensionResource(R.dimen.padding_start_text),
                    end = dimensionResource(R.dimen.padding_end_text),
                    bottom = dimensionResource(R.dimen.padding_bot_desc)
                )
            )
        }

        AppBar(
            canNavigateBack = canNavigateBack,
            navigateUp = navigateUp
        )
    }
}