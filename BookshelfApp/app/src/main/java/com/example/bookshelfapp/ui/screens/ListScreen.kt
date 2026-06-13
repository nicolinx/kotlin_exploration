package com.example.bookshelfapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.bookshelfapp.ui.theme.BookshelfAppTheme

@Composable
fun ListScreen(contentPadding: PaddingValues) {
    val mocks = listOf("1", "2", "3", "4")
    val layoutDirection = LocalLayoutDirection.current

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(
            top = contentPadding.calculateTopPadding() + 12.dp,
            bottom = contentPadding.calculateBottomPadding() + 12.dp,
            start = contentPadding.calculateStartPadding(layoutDirection) + 12.dp,
            end = contentPadding.calculateEndPadding(layoutDirection) + 12.dp,
        ),
    ) {
        items(mocks) { item ->
            ItemCard()
        }
    }
}

@Composable
fun ItemCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .aspectRatio(0.8f / 1f)
    ) {
        AsyncImage(
            model = "https://books.google.com/books/content?id=3bJZEAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api",
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ListScreenPreview() {
    BookshelfAppTheme() {
        ListScreen(contentPadding = PaddingValues(12.dp))
    }
}