package com.example.bookshelfapp.ui

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.bookshelfapp.ui.screens.ListScreen

@Composable
fun App() {
    Scaffold() { innerPadding ->
        ListScreen(contentPadding = innerPadding)
    }
}

