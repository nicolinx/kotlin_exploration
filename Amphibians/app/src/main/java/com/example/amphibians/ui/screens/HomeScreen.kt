package com.example.amphibians.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibians.R
import com.example.amphibians.models.AnimalModel
import com.example.amphibians.ui.theme.AmphibiansTheme

@Composable
fun HomeScreen(homeUiState: HomeUiState, modifier: Modifier = Modifier) {
    Surface(modifier = modifier) {
        when (homeUiState) {
            is HomeUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
            is HomeUiState.Error -> ErrorScreen(modifier = Modifier.fillMaxSize())
            is HomeUiState.Success -> {
                SuccessScreen(animals = homeUiState.animals, modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun SuccessScreen(animals: List<AnimalModel>, modifier: Modifier) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(items = animals, key = { item -> item.name }) { item ->
            ItemCard(item, modifier = Modifier.fillMaxWidth())
        }
    }
}

@Composable
fun ItemCard(
    animal: AnimalModel,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Column {
            Text(
                "${animal.name} (${animal.type})", modifier = Modifier.padding(12.dp),
                style = MaterialTheme.typography.titleMedium
            )
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(animal.imgSrc)
                    .crossfade(true)
                    .build(),
                error = painterResource(R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_img),
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f),
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )
            Text(
                animal.description,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(12.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(R.drawable.ic_connection_error), contentDescription = null)
        Text(
            stringResource(R.string.loading_failed), style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    AmphibiansTheme() {
//        HomeScreen(, modifier = Modifier.fillMaxSize())
    }
}