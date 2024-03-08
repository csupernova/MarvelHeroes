package com.example.marvelheroes.ui


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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.example.marvelheroes.R
import com.example.marvelheroes.data.InfoHeroes



@SuppressLint("UnusedBoxWithConstraintsScope")
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    heroes: List<InfoHeroes>,
    onCardClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.primary
    ) {
        FilledTriangle(MaterialTheme.colorScheme.secondary)

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(modifier = Modifier.padding(top=dimensionResource(R.dimen.padding_large))
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

            val lazyListState = rememberLazyListState()
            val flingBehavior = rememberSnapFlingBehavior(lazyListState = lazyListState)

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
                        Card(
                            modifier = Modifier
                                .height(dimensionResource(R.dimen.height_card))
                                .width(dimensionResource(R.dimen.width_card)),
                            onClick = {onCardClick(index)}

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
                                        start=dimensionResource(R.dimen.padding_name_start),
                                        top=dimensionResource(R.dimen.padding_name_top)))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FilledTriangle(fillColor: Color) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val width = size.width
        val height = size.height

        // Координаты вершин треугольника
        val point1 = Offset(0f, height)
        val point2 = Offset(width, height/2)
        val point3 = Offset(size.width, height)

        // Создаем путь для треугольника
        val path = Path().apply {
            moveTo(point1.x, point1.y)
            lineTo(point2.x, point2.y)
            lineTo(point3.x, point3.y)
            close() // Закрываем путь
        }

        // Рисуем треугольник с заливкой выбранным цветом
        drawPath(
            path = path,
            color = fillColor,
        )
    }
}