package com.example.marvelheroes.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.marvelheroes.R

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    canNavigateBack: Boolean = false,
    navigateUp: () -> Unit = {}
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.primary
    ) {
        Box {
            Column(
                modifier = modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_connection_error),
                    contentDescription = stringResource(R.string.connection_error)
                )
                Text(
                    text = stringResource(R.string.failed_to_load),
                    modifier = Modifier.padding(16.dp)
                )
            }
            AppBar(
                canNavigateBack = canNavigateBack,
                navigateUp = navigateUp
            )
        }
    }
}