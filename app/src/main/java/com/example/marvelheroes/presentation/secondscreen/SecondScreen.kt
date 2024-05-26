package com.example.marvelheroes.presentation.secondscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.marvelheroes.R
import com.example.marvelheroes.presentation.HeroUi
import com.example.marvelheroes.presentation.components.AppBar
import com.example.marvelheroes.presentation.components.ErrorScreen



@Composable
fun SecondScreen(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
) {
    val heroViewModel = hiltViewModel<HeroViewModel>()

    val heroState = heroViewModel.heroState.collectAsState().value
    heroViewModel.getHeroById()

    if (heroState.isError) {
        ErrorScreen(
            modifier = Modifier.fillMaxSize(),
            canNavigateBack = canNavigateBack,
            navigateUp = navigateUp
        )
    }
    else if (heroState.hero != null) {
        ResultSecondScreen(
            hero = heroState.hero,
            canNavigateBack = canNavigateBack,
            navigateUp = navigateUp,
        )
    }
}

@Composable
fun ResultSecondScreen(
    hero: HeroUi,
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
