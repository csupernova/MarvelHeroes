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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.marvelheroes.R
import com.example.marvelheroes.data.InfoHeroes
import kotlin.math.abs



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
        FilledTriangle(MaterialTheme.colorScheme.error)
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(modifier = Modifier.padding(top=40.dp)) {
                Image(
                    contentDescription = null,
                    painter = painterResource(R.drawable.logo_marvel),
                    modifier = Modifier
                        .size(width = 150.dp, height = 40.dp)
                )
            }
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = stringResource(R.string.title),
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )

            val lazyListState = rememberLazyListState()
            val flingBehavior = rememberSnapFlingBehavior(lazyListState = lazyListState)
            BoxWithConstraints {
                LazyRow(
                    verticalAlignment = Alignment.CenterVertically,
                    state = lazyListState,
                    flingBehavior = flingBehavior,
                    contentPadding = PaddingValues(
                        start = 65.dp,
                        end = 65.dp
                    ),
                    horizontalArrangement = Arrangement.spacedBy(30.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    itemsIndexed(heroes) { index, item ->
                        Card(
                            modifier = Modifier
                                //.scale(opacity)
                                .height(500.dp)
                                .width(260.dp),
                            onClick = {onCardClick(index)}

                        ) {
                            Box {
                                AsyncImage(
                                    model = stringResource(item.picture),
                                    contentDescription = stringResource(item.name),
                                    contentScale = ContentScale.FillBounds
                                )
                                Text(
                                    text = stringResource(item.name),
                                    color = Color.White,
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(start=30.dp, top=400.dp)
                                )
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