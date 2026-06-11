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
import com.example.amphibians.MainApplication
import com.example.amphibians.models.AnimalModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

sealed interface HomeUiState {
    data class Success(val animals: List<AnimalModel>) : HomeUiState
    object Loading : HomeUiState
    object Error : HomeUiState
}

class HomeViewModel : ViewModel() {
    var homeUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getAnimals()
    }

    fun getAnimals() {
        viewModelScope.launch {
            homeUiState = HomeUiState.Loading
            delay(1000)
            homeUiState = HomeUiState.Success(
                animals = listOf(
                    AnimalModel(
                        name = "Great Basin Spadefoot",
                        type = "Toad",
                        description = "This toad spends most of its life underground due to the arid desert conditions in which it lives. Spadefoot toads earn the name because of their hind legs which are wedged to aid in digging. They are typically grey, green, or brown with dark spots.",
                        imgSrc = "https://developer.android.com/codelabs/basic-android-kotlin-compose-amphibians-app/img/great-basin-spadefoot.png"
                    ),
                    AnimalModel(
                        name = "Roraima Bush Toad",
                        type = "Toad",
                        description = "This toad is typically found in South America. Specifically on Mount Roraima at the boarders of Venezuala, Brazil, and Guyana, hence the name. The Roraiam Bush Toad is typically black with yellow spots or marbling along the throat and belly.",
                        imgSrc = "https://developer.android.com/codelabs/basic-android-kotlin-compose-amphibians-app/img/roraima-bush-toad.png"
                    ),
                    AnimalModel(
                        name = "Pacific Chorus Frog",
                        type = "Frog",
                        description = "Also known as the Pacific Treefrog, it is the most common frog on the Pacific Coast of North America. These frogs can vary in color between green and brown and can be identified by a brown stripe that runs from their nostril, through the eye, to the ear.",
                        imgSrc = "https://developer.android.com/codelabs/basic-android-kotlin-compose-amphibians-app/img/pacific-chorus-frog.png"
                    ),
                    AnimalModel(
                        name = "Blue Jeans Frog",
                        type = "Frog",
                        description = "Sometimes called the Strawberry Poison-Dart Frog, this little amphibian is identifiable by its bright red body and blueish-purple arms and legs. The Blue Jeans Frog is not toxic to humans like some of its close relatives, but it can be harmful to some predators.",
                        imgSrc = "https://developer.android.com/codelabs/basic-android-kotlin-compose-amphibians-app/img/blue-jeans-frog.png"
                    ),
                    AnimalModel(
                        name = "California Giant Salamander",
                        type = "Salamander",
                        description = "As the name implies, this salamander can be found in Northern California, as well as other parts of the Pacific Northwest. They prefer temperate areas with plenty of moisture and they typically can grow up to 30 cm.",
                        imgSrc = "https://developer.android.com/codelabs/basic-android-kotlin-compose-amphibians-app/img/california-giant-salamander.png"
                    ),
                    AnimalModel(
                        name = "Tiger Salamander",
                        type = "Salamander",
                        description = "Tiger Salamanders are typically found on the Atlantic Coast of North America. They are named for their coloring which is typically a brown body broken up by yellowish-greensish spots. While they like moist conditions, they don't spend very much time in bodies of water. Instead they prefer to burrow into loose soil.",
                        imgSrc = "https://developer.android.com/codelabs/basic-android-kotlin-compose-amphibians-app/img/tiger-salamander.png"
                    )
                )
            )
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MainApplication)
                HomeViewModel()
            }
        }
    }

//    companion object {
//        val Factory: ViewModelProvider.Factory = viewModelFactory {
//            initializer {
//                val application = (this[APPLICATION_KEY] as MarsPhotosApplication)
//                val marsPhotosRepository = application.container.marsPhotosRepository
//                MarsViewModel(marsPhotosRepository = marsPhotosRepository)
//            }
//        }
//    }
}