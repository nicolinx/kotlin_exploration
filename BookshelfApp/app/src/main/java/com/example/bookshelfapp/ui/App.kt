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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bookshelfapp.R
import com.example.bookshelfapp.ui.screens.DetailScreen
import com.example.bookshelfapp.ui.screens.ListScreen
import com.example.bookshelfapp.ui.screens.DetailViewModel
import com.example.bookshelfapp.ui.screens.ListViewModel
import com.example.bookshelfapp.ui.theme.BookshelfAppTheme

@Composable
fun App() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { MainTopAppBar() }
    ) { innerPadding ->
        NavHost(navController = navController, startDestination = "list_page") {
            composable("list_page") {
                val viewModel: ListViewModel = viewModel(factory = ListViewModel.Factory)
                ListScreen(uiState = viewModel.listUiState, contentPadding = innerPadding)
            }

            composable("detail_page/{bookId") {
                val viewModel: DetailViewModel = viewModel(factory = DetailViewModel.Factory)
                DetailScreen(uiState = viewModel.detailUiState, contentPadding = innerPadding)
            }
        }
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