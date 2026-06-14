package com.example.bookshelfapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.bookshelfapp.ui.shared.InfoText
import com.example.bookshelfapp.ui.theme.BookshelfAppTheme

@Composable
fun DetailScreen(uiState: DetailUiState, contentPadding: PaddingValues) {

    when (uiState) {
        is DetailUiState.Loading -> InfoText("LOADING")
        is DetailUiState.Error -> InfoText("ERROR")
        is DetailUiState.Success ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(contentPadding)
            ) {
                AsyncImage(
                    model = uiState.book.volumeInfo?.imageLinks?.thumbnail?.replace(
                        "http://",
                        "https://"
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(0.8f / 1f)
                )
                Column(
                    modifier = Modifier.padding(12.dp)
                ) {
                    Text(
                        uiState.book.volumeInfo?.title ?: "-",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(Modifier.height(12.dp))
                    Text(
                        uiState.book.volumeInfo?.description ?: "-",
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    BookshelfAppTheme() {
        DetailScreen(DetailUiState.Loading, contentPadding = PaddingValues(12.dp))
    }
}