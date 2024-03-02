package com.example.marvelheroes.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.marvelheroes.R

@Composable
fun Title() {
    Column {
        Text(
            text = stringResource(R.string.title),
            color = Color.White,
            modifier = Modifier
                .padding(top=30.dp)
        )
    }
}