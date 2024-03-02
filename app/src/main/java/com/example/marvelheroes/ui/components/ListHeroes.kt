package com.example.marvelheroes.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.abs

@SuppressLint("UnusedBoxWithConstraintsScope")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListHeroes() {
    val items = remember {
        ('A'..'Z').map { it.toString() }
    }
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    val lazyListState = rememberLazyListState()
    val flingBehavior = rememberSnapFlingBehavior(lazyListState = lazyListState)
    BoxWithConstraints {
        val halfRowWidth = constraints.maxWidth / 2
        LazyRow(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            state = lazyListState,
            flingBehavior = flingBehavior
        ) {
            itemsIndexed(items) { index, item ->
                val opacity by remember {
                    derivedStateOf {
                        val currentItemInfo = lazyListState.layoutInfo.visibleItemsInfo
                            .firstOrNull { it.index == index }
                            ?: return@derivedStateOf 0.5f
                        val itemHalfSize = currentItemInfo.size / 2
                        (1f - minOf(
                            1f,
                            abs(currentItemInfo.offset + itemHalfSize - halfRowWidth).toFloat() / halfRowWidth
                        ) * 0.5f)
                    }
                }
                Card(
                    modifier = Modifier
                        .scale(opacity)
                        .height(400.dp)
                        .width(200.dp)
                        .padding(8.dp)
                        .background(Color.Gray),
                ) {
                    Text(item, fontSize = 32.sp)
                }
            }
        }
    }
}
