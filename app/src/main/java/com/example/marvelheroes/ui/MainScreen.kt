package com.example.marvelheroes.ui



import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marvelheroes.R
import com.example.marvelheroes.ui.components.FilledTriangle
import com.example.marvelheroes.ui.components.ListHeroes



@Preview
@Composable
fun MainScreen(
    modifier: Modifier = Modifier
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
            ListHeroes()
        }
    }
}
