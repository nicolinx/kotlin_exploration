package com.example.mycityapp.ui

import com.example.mycityapp.model.Category
import com.example.mycityapp.model.Item

data class MyCityState(
    val listCategories: List<Category>,
    val listItems: List<Item>,
    val selectedItem: Item
)
