package com.example.mycityapp.ui

import androidx.lifecycle.ViewModel
import com.example.mycityapp.data.LocalDataProvider
import com.example.mycityapp.model.Category
import com.example.mycityapp.model.Item
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MyCityViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(
        MyCityState(
            listCategories = LocalDataProvider.getCategoriesData()
        )
    )

    val uiState: StateFlow<MyCityState> = _uiState.asStateFlow()

    fun selectCategory(category: Category) {
        _uiState.update { it ->
            it.copy(
                selectedCategory = category,
                listItems = LocalDataProvider.getItemsByCategoryId(category.id)
            )
        }
    }

    fun unselectCategory() {
        _uiState.update { it ->
            it.copy(
                selectedCategory = null,
                listItems = emptyList()
            )
        }
    }

    fun selectCategoryExpanded(category: Category) {
        _uiState.update { it ->
            val items = LocalDataProvider.getItemsByCategoryId(category.id)
            it.copy(
                selectedCategory = category,
                listItems = items,
                selectedItem = items[0]
            )
        }
    }

    fun selectItem(item: Item) {
        _uiState.update { it ->
            it.copy(selectedItem = item)
        }
    }

    fun unselectItem() {
        _uiState.update { it ->
            it.copy(selectedItem = null)
        }
    }
}