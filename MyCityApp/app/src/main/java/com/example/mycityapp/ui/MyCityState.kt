package com.example.mycityapp.ui

import com.example.mycityapp.model.Category
import com.example.mycityapp.model.Item

data class MyCityState(
    val listCategories: List<Category> = emptyList(),
    val listItems: List<Item> = emptyList(),
    val selectedCategory: Category? = null,
    val selectedItem: Item? = null,
)
