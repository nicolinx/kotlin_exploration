package com.example.amphibians.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import coil.network.HttpException
import com.example.amphibians.MainApplication
import com.example.amphibians.data.AnimalRepository
import com.example.amphibians.models.AnimalModel
import kotlinx.coroutines.launch
import okio.IOException

sealed interface HomeUiState {
    data class Success(val animals: List<AnimalModel>) : HomeUiState
    object Loading : HomeUiState
    object Error : HomeUiState
}

class HomeViewModel(private val animalRepository: AnimalRepository) : ViewModel() {
    var homeUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getAnimals()
    }

    fun getAnimals() {
        viewModelScope.launch {
            homeUiState = HomeUiState.Loading
            homeUiState = try {
                HomeUiState.Success(animalRepository.getAmphibians())
            } catch (e: IOException) {
                HomeUiState.Error
            } catch (e: HttpException) {
                HomeUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MainApplication)
                val animalRepository = application.container.animalRepository
                HomeViewModel(animalRepository)
            }
        }
    }
}