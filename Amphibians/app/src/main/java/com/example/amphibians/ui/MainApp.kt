package com.example.amphibians.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.amphibians.R
import com.example.amphibians.ui.screens.HomeScreen
import com.example.amphibians.ui.screens.HomeViewModel

@Composable
fun MainApp() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { MainTopBar() }
    ) { innerPadding ->
        val viewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory)
        HomeScreen(
            homeUiState = viewModel.homeUiState,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(modifier: Modifier = Modifier) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(stringResource(R.string.app_name))
        }
    )
}