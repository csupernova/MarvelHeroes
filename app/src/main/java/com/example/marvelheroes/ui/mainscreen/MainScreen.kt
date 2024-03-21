package com.example.marvelheroes.ui.mainscreen


import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.marvelheroes.R
import com.example.marvelheroes.network.models.Hero
import com.example.marvelheroes.ui.MainScreenUiState
import com.example.marvelheroes.ui.SelectViewModel
import com.example.marvelheroes.ui.components.ErrorScreen
import com.example.marvelheroes.ui.components.HeroCard
import com.example.marvelheroes.ui.components.LoadingScreen


@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun MainScreen(
    onCardClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    selectViewModel: SelectViewModel,
    mainScreenUiState: MainScreenUiState
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.primary
    ) {
        FilledTriangle(MaterialTheme.colorScheme.secondary)

        when (mainScreenUiState) {
            is MainScreenUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
            is MainScreenUiState.Success ->
                ResultScreen(
                    heroes = mainScreenUiState.heroes,
                    onCardClick = onCardClick,
                    selectViewModel = selectViewModel
                )
            is MainScreenUiState.Error -> ErrorScreen(modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
fun ResultScreen(
    heroes: List<Hero>,
    modifier: Modifier = Modifier,
    onCardClick: (String) -> Unit,
    selectViewModel: SelectViewModel
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        FirstScreen(
            heroes = heroes,
            onCardClick = onCardClick,
            selectViewModel = selectViewModel
        )
    }
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FirstScreen(
    heroes: List<Hero>,
    onCardClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    selectViewModel: SelectViewModel,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        val lazyListState = rememberLazyListState()
        val flingBehavior = rememberSnapFlingBehavior(lazyListState = lazyListState)

        Box(modifier = modifier.padding(top=dimensionResource(R.dimen.padding_large))
        ) {
            Image(
                contentDescription = null,
                painter = painterResource(R.drawable.logo_marvel),
                modifier = Modifier
                    .size(
                        width = dimensionResource(R.dimen.width_logo),
                        height = dimensionResource(R.dimen.height_logo))
            )
        }
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_large)))

        Text(
            text = stringResource(R.string.title),
            color = MaterialTheme.colorScheme.tertiary,
            style = MaterialTheme.typography.titleLarge
        )

        BoxWithConstraints {
            LazyRow(
                verticalAlignment = Alignment.CenterVertically,
                state = lazyListState,
                flingBehavior = flingBehavior,
                contentPadding = PaddingValues(
                    start = dimensionResource(R.dimen.padding_lazyrow),
                    end = dimensionResource(R.dimen.padding_lazyrow)
                ),
                horizontalArrangement = Arrangement.spacedBy(
                    dimensionResource(R.dimen.padding_btwn_cards)
                ),
                modifier = Modifier.fillMaxSize()
            ) {
                itemsIndexed(heroes) { index, item ->
                    HeroCard(index, item, onCardClick, selectViewModel)
                }
            }
        }
    }
}

@Composable
private fun FilledTriangle(fillColor: Color) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val width = size.width
        val height = size.height

        val point1 = Offset(0f, height)
        val point2 = Offset(width, height/2)
        val point3 = Offset(size.width, height)

        val path = Path().apply {
            moveTo(point1.x, point1.y)
            lineTo(point2.x, point2.y)
            lineTo(point3.x, point3.y)
            close()
        }

        drawPath(
            path = path,
            color = fillColor,
        )
    }
}