package com.example.marvelheroes.ui.components



import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.marvelheroes.ui.theme.AppTheme


@Preview
@Composable
fun MainScreen() {
    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.primary
        )
        {
            FilledTriangle(MaterialTheme.colorScheme.error)
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Logo()
                Title()
                ListHeroes()
            }
        }
    }
}
