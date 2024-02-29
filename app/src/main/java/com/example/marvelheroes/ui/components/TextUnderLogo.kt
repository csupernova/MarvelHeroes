package com.example.marvelheroes.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun TextUnderLogo() {
    Column {
        Text(
            text = "Choose your hero",
            color = Color.White,
            modifier = Modifier
                .padding(top=30.dp)
        )
    }
}