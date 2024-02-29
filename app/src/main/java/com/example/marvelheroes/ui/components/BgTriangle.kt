package com.example.marvelheroes.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path

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