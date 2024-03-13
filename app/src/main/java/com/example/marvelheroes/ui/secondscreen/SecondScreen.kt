package com.example.marvelheroes.ui.secondscreen

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
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.example.marvelheroes.R
import com.example.marvelheroes.data.InfoHeroes
import com.example.marvelheroes.ui.components.AppBar


@Composable
fun SecondScreen(
    hero: InfoHeroes,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = stringResource(hero.picture),
            contentDescription = stringResource(hero.name),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier.fillMaxSize()
        ) {

            Text(
                text = stringResource(hero.name),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.padding(
                    start = dimensionResource(R.dimen.padding_start_text),
                    bottom = dimensionResource(R.dimen.padding_bot_name)
                ),
            )

            Text(
                text = stringResource(hero.description),
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