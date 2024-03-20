package com.example.marvelheroes.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.example.marvelheroes.ui.SelectViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroCard(
    index: Int,
    item: InfoHeroes,
    onCardClick: (Int) -> Unit,
    selectViewModel: SelectViewModel
) {
    Card(
        modifier = Modifier
            .height(dimensionResource(R.dimen.height_card))
            .width(dimensionResource(R.dimen.width_card)),
        onClick = {
            selectViewModel.setCard(index)
            onCardClick(index)
        }

    ) {
        Box {
            AsyncImage(
                model = stringResource(item.picture),
                contentDescription = stringResource(item.name),
                contentScale = ContentScale.Crop
            )
            Text(
                text = stringResource(item.name),
                color = MaterialTheme.colorScheme.tertiary,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(
                    start= dimensionResource(R.dimen.padding_name_start),
                    top= dimensionResource(R.dimen.padding_name_top)
                ))
        }
    }

}