package com.example.bookshelfapp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookshelfapp.R
import com.example.bookshelfapp.ui.screens.DetailScreen
import com.example.bookshelfapp.ui.theme.BookshelfAppTheme

@Composable
fun App() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { MainTopAppBar() }
    ) { innerPadding ->
//        ListScreen(contentPadding = innerPadding)
        DetailScreen(contentPadding = innerPadding)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar() {
    TopAppBar(title = {
        Text(stringResource(R.string.app_name))
    })
}

@Preview
@Composable
fun AppPreview() {
    BookshelfAppTheme() {
        App()
    }
}