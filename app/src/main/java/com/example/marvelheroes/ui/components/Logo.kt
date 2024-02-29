package com.example.marvelheroes.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.marvelheroes.R

@Preview
@Composable
fun Logo() {
    Column(modifier = Modifier.padding(top=30.dp)) {
        Image(
            contentDescription = null,
            painter = painterResource(R.drawable.logo_marvel),
            modifier = Modifier
                .size(width = 128.dp, height = 27.dp)
        )
    }
}