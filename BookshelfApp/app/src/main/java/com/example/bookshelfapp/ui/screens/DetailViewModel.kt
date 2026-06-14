package com.example.bookshelfapp.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelfapp.core.DefaultApplication
import com.example.bookshelfapp.model.Book
import com.example.bookshelfapp.network.BooksRepository
import kotlinx.coroutines.launch

sealed interface DetailUiState {
    data class Success(val book: Book) : DetailUiState
    object Loading : DetailUiState
    object Error : DetailUiState
}

class DetailViewModel(
    private val booksRepository: BooksRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val bookId: String = checkNotNull(savedStateHandle["bookId"])

    var detailUiState: DetailUiState by mutableStateOf(DetailUiState.Loading)
        private set

    init {
        viewModelScope.launch {
            detailUiState = DetailUiState.Loading
            detailUiState = try {
                val book = booksRepository.getBookDetail(id = bookId);
                DetailUiState.Success(book)
            } catch (e: Exception) {
                Log.e("Error Exception:", e.toString())
                DetailUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as DefaultApplication)
                val booksRepository = application.container.booksRepository

                DetailViewModel(
                    booksRepository = booksRepository,
                    savedStateHandle = this.createSavedStateHandle()
                )
            }
        }
    }
}