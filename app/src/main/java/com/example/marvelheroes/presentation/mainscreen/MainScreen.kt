package com.example.marvelheroes.presentation.mainscreen


import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.marvelheroes.R
import com.example.marvelheroes.presentation.HeroUi
import com.example.marvelheroes.presentation.components.ErrorScreen
import com.example.marvelheroes.presentation.components.HeroCard
import com.example.marvelheroes.presentation.components.LoadingScreen


@Composable
fun MainScreen(
    onCardClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val heroListViewModel: HeroListViewModel = hiltViewModel<HeroListViewModel>()

    val heroListState = heroListViewModel.heroListState.collectAsState().value

    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.primary
    ) {
        FilledTriangle(MaterialTheme.colorScheme.secondary)

        if (heroListState.isLoading) LoadingScreen(modifier = Modifier.fillMaxSize())
        else if (heroListState.isError) ErrorScreen(modifier = Modifier.fillMaxSize())
        else ResultMainScreen(heroes = heroListState.heroList, onCardClick = onCardClick )
    }
}

@Composable
fun ResultMainScreen(
    heroes: List<HeroUi>,
    modifier: Modifier = Modifier,
    onCardClick: (String) -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        FirstScreen(
            heroes = heroes,
            onCardClick = onCardClick,
        )
    }
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FirstScreen(
    heroes: List<HeroUi>,
    onCardClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val isLandscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        val lazyListState = rememberLazyListState()
        val flingBehavior = rememberSnapFlingBehavior(lazyListState = lazyListState)

        Box(modifier = modifier.padding(
            top = when(isLandscape) {
                false -> dimensionResource(R.dimen.padding_large)
                true -> 0.dp
            }
        )
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
        Spacer(modifier = Modifier.height(
            when (isLandscape) {
                false -> dimensionResource(R.dimen.padding_large)
                true -> 10.dp
            })
        )

        Text(
            text = stringResource(R.string.title),
            color = MaterialTheme.colorScheme.tertiary,
            style = MaterialTheme.typography.titleLarge
        )

        BoxWithConstraints{
            LazyRow(
                verticalAlignment = Alignment.CenterVertically,
                state = lazyListState,
                flingBehavior = flingBehavior,
                contentPadding = PaddingValues(
                    horizontal = dimensionResource(R.dimen.padding_lazyrow)
                ),
                horizontalArrangement = Arrangement.spacedBy(
                    dimensionResource(R.dimen.padding_btwn_cards)
                ),
                modifier = Modifier.fillMaxSize()
            ) {
                itemsIndexed(heroes) { _, item ->
                    HeroCard(item, onCardClick)
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