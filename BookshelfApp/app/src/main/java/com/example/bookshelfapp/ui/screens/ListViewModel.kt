package com.example.bookshelfapp.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelfapp.core.DefaultApplication
import com.example.bookshelfapp.model.Book
import com.example.bookshelfapp.network.BooksRepository
import kotlinx.coroutines.launch

sealed interface ListUiState {
    data class Success(val books: List<Book>) : ListUiState
    object Loading : ListUiState
    object Error : ListUiState
}

class ListViewModel(private val booksRepository: BooksRepository) : ViewModel() {
    var listUiState: ListUiState by mutableStateOf(ListUiState.Loading)
        private set

    init {
        viewModelScope.launch {
            listUiState = ListUiState.Loading
            listUiState = try {
                val books = booksRepository.getBooks("android");
                ListUiState.Success(books)
            } catch (e: Exception) {
                Log.e("Error Exception:", e.toString())
                ListUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as DefaultApplication)
                val booksRepository = application.container.booksRepository
                ListViewModel(booksRepository)
            }
        }
    }
}